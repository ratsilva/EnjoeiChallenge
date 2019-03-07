package br.com.enjoeichallenge.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ProductListAdapter;
import br.com.enjoeichallenge.controllers.ProductController;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.objects.User;
import br.com.enjoeichallenge.views.widgets.ErrorView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment {

    private Unbinder unbinder;
    private ProductListAdapter productListAdapter;

    public ProductController productController;
    public ArrayList<Object> listProdutos;

    @BindView(R.id.fragment_productlist_swipelayout) SwipeRefreshLayout swipeLayout;
    @BindView(R.id.fragment_productlist_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.fragment_error_btn) Button btnError;
    @BindView(R.id.fragment_product_error) ErrorView errorFragment;

    private boolean noInternet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productlist, container, false);

        // Bind fragment with ButterKnife
        unbinder = ButterKnife.bind(this, view);

        // Initializing
        listProdutos = new ArrayList<>();
        productController = new ProductController(getContext());

        // Configure Adapter and Recycler
        productListAdapter = new ProductListAdapter(listProdutos, getContext());
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(productListAdapter);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
            return productListAdapter.isPositionHeader(position) ? manager.getSpanCount() : 1;
            }
        });

        // Define Pull to Refresh
        defineSwipeRefresh();

        // Busca produtos na API REST
        getProductsAPI();

        return view;
    }

    private void defineSwipeRefresh(){

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProductsAPI();
                swipeLayout.setRefreshing(false);
            }
        });

    }

    public void getProductsAPI(){

        // Busca produtos via API e salva no banco de dados

        if(!productController.testConnection()){
            noInternet = true;
            return;
        }

        Call<Product.ProductJson> listProducts = productController.httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {

            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {

                Log.v("onResponse", response.body().toString());

                // Deletar infos do BD
                productController.deleteAllBD();

                // pegar a resposta
                Product.ProductJson prodJson = response.body();

                for(Product p : prodJson.getProduts()){

                    // Usuário
                    User user = p.getUser();
                    long id_user = productController.sqlUser.save(user);

                    // Inserir produto
                    p.setId_user(id_user);
                    productController.sqlProduct.save(p);

                }

                getProductsBD();

            }

            @Override
            public void onFailure(Call<Product.ProductJson> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

    }

    public void defineLayout(){

        if(listProdutos.size() == 0 && !noInternet){
            errorFragment.setVisibility(View.VISIBLE);
        }else{
            errorFragment.setVisibility(View.GONE);
        }

    }

    public void getProductsBD(){

        // Busca produtos no BD
        listProdutos = productController.getListProducts();

        // Se não existem produtos no BD
        if(listProdutos == null)listProdutos = new ArrayList<>();

        // Update list do adapter
        onLoadCompleted();

        // Update layout usuário
        defineLayout();

    }

    public void onLoadCompleted(){
        //update adapter
        productListAdapter.refreshList(listProdutos);
    }

    @OnClick(R.id.fragment_error_btn)
    public void onClick(){
        getProductsAPI();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}

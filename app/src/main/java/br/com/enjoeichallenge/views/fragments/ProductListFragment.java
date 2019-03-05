package br.com.enjoeichallenge.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ProductListAdapter;
import br.com.enjoeichallenge.controllers.ProductController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment {

    private Unbinder unbinder;
    private ProductController productController;
    private ArrayList<Object> listProdutos;
    private ProductListAdapter productListAdapter;

    @BindView(R.id.fragment_productlist_swipelayout) SwipeRefreshLayout swipeLayout;
    @BindView(R.id.fragment_productlist_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.fragment_productlist_errorview) LinearLayout fragmentError;
    @BindView(R.id.fragment_error_btn) Button btnError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productlist, container, false);

        // Bind fragment with ButterKnife
        unbinder = ButterKnife.bind(this, view);

        // Define Product Controller
        productController = new ProductController(getContext());

        // Load Products
        loadProducts();

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

        return view;
    }



    private void defineSwipeRefresh(){

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadProducts();
                swipeLayout.setRefreshing(false);
                onLoadCompleted();
            }
        });


    }

    private void loadProducts(){

        boolean error = false;

        // Busca produtos via API e salva no banco de dados
        boolean okAPI = productController.requestProductsAPI();

        // Busca produtos no BD
        listProdutos = productController.getListProducts();

        // Se não existem produtos no BD por falta de conexão
        if(listProdutos == null){
            // Error view
            error = true;
            listProdutos = new ArrayList<>();
        }

        defineErrorView(error);

    }

    private void onLoadCompleted(){

        //update adapter
        productListAdapter.refreshList(listProdutos);

    }

    private void defineErrorView(boolean error){
        if(error) {
            swipeLayout.setVisibility(View.GONE);
            fragmentError.setVisibility(View.VISIBLE);
        }else{
            fragmentError.setVisibility(View.GONE);
            swipeLayout.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.fragment_error_btn)
    public void onClick(){

        loadProducts();
        onLoadCompleted();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}

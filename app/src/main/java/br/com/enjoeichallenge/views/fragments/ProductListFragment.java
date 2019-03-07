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

    // Variáveis auxiliares de funcionamento
    private Unbinder unbinder;
    private ProductListAdapter productListAdapter;
    public ProductController productController;
    public ArrayList<Object> listProdutos;
    private boolean connectInternet;

    // Referenciando variáveis com o layout através da biblioteca ButterKnife
    @BindView(R.id.fragment_productlist_swipelayout) SwipeRefreshLayout swipeLayout;
    @BindView(R.id.fragment_productlist_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.fragment_error_btn) Button btnError;
    @BindView(R.id.fragment_product_error) ErrorView errorFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productlist, container, false);

        // Bind fragment com ButterKnife
        unbinder = ButterKnife.bind(this, view);

        // Inicializando auxiliares de funcionamento
        listProdutos = new ArrayList<>();
        productController = new ProductController(getContext());

        // Configurando recyclerView
        setupRecyclerView();

        // Configuração Swipe
        setupSwipe();

        // Busca produtos na API REST
        getProductsAPI();

        return view;
    }

    /**
     *  Cria um adapter
     *  Define recyclerView com 2 colunas
     *  Define header do recyclerView
     *  Adiciona adapter no recyclerView
     */
    private void setupRecyclerView(){

        // Criando adapter da listagem
        productListAdapter = new ProductListAdapter(listProdutos, getContext());

        // Definindo recycler com 2 colunas
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);

        // Colocando o header para ocupar 2 posições horizontais no grid
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return productListAdapter.isPositionHeader(position) ? manager.getSpanCount() : 1;
            }
        });

        // Adicionando adapter ao recycler
        recyclerView.setAdapter(productListAdapter);

    }

    /**
     *  Configura o evento de pull to refresh do swipeLayout
     *  Configura a cor do loading do swipeLayout
     */
    private void setupSwipe(){

        // Configurando swipe to refresh
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProductsAPI();
            }
        });

        // Definindo cor do loading do swipe
        swipeLayout.setColorSchemeResources(R.color.rosa);

    }

    /**
     *  Busca produtos via API e salva no banco de dados
     *  Chama método getProductsBD() para atualizar interface
     *
     */
    public void getProductsAPI(){


        // Verifica se existe conexão com a internet
        if(!productController.testConnection()){

            // Define variável como sem internet
            connectInternet = false;

            // Busca informações no BD para atualizar a tela
            getProductsBD();

            return;
        }

        swipeLayout.setRefreshing(true);

        Call<Product.ProductJson> listProducts = productController.httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {

            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {

                // Limpa informações do banco de dados
                productController.deleteAllBD();

                // Parse da resposta da API
                Product.ProductJson prodJson = response.body();

                // Para cada Produto
                for(Product p : prodJson.getProduts()){

                    // Salvar Usuário
                    User user = p.getUser();
                    long id_user = productController.sqlUser.save(user);

                    // Salvar Produto
                    p.setId_user(id_user);
                    productController.sqlProduct.save(p);

                }

                // Busca informações no BD para atualizar a tela
                getProductsBD();

            }

            @Override
            public void onFailure(Call<Product.ProductJson> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

    }

    /**
     *  Busca produtos no banco de dados
     *  Atualiza adapter do recyclerView
     *  Atualiza layout do usuário
     */
    public void getProductsBD(){

        // Busca produtos no BD
        listProdutos = productController.getListProducts();

        // Se não existem produtos no BD, inicializa variável novamente
        if(listProdutos == null)listProdutos = new ArrayList<>();

        // Atualiza adapter do recyclerView
        onLoadCompleted();

        // Atualiza layout do usuário
        defineLayout();

    }

    /**
     *   Atualiza adapter do recyclerView
     */
    public void onLoadCompleted(){
        //update adapter
        productListAdapter.refreshList(listProdutos);
        swipeLayout.setRefreshing(false);
    }

    /**
     *   Verifica se o layout de erro deve ser mostrado ou não
     */
    public void defineLayout(){

        if(listProdutos.size() == 0 && !connectInternet){
            errorFragment.setVisibility(View.VISIBLE);
        }else{
            errorFragment.setVisibility(View.GONE);
        }

    }

    /**
     *  Evento do botão tentar de novo
     *  Busca produtos na API REST novamente
     */
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

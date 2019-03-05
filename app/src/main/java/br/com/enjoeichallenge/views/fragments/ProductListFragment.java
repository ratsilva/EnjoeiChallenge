package br.com.enjoeichallenge.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.controllers.ProductController;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.views.activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment {

    private Unbinder unbinder;
    ProductController productController;
    ArrayList<Object> listProdutos;

    @BindView(R.id.fragment_productlist_swipelayout) SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productlist, container, false);
        unbinder = ButterKnife.bind(this, view);

        productController = new ProductController(getContext());

        return view;
    }

    private void carregaProdutos(){

        // Busca produtos via API e salva no banco de dados
        boolean okAPI = productController.requestProductsAPI();

        // Busca produtos no BD
        listProdutos = productController.getListProducts();

        // Se não existem produtos no BD por falta de conexão
        if(listProdutos == null && !okAPI){


        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}

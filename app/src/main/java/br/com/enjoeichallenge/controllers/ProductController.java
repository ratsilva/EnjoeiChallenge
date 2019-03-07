package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.com.enjoeichallenge.adapters.ProductListAdapter;
import br.com.enjoeichallenge.objects.User;
import br.com.enjoeichallenge.tools.restapi.HTTPRequest_Product;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.views.activities.MainActivity;
import br.com.enjoeichallenge.views.fragments.ProductListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController extends Controller {

    Context ctx;

    public HTTPRequest_Product httpProduct;

    public ProductController(Context ctx_){
        super(ctx_);
        ctx = ctx_;
        httpProduct = http.getProductService();
    }

    public ArrayList<Object> getListProducts(){
        return sqlProduct.selectAll("");
    }

    public Object getProduct(long idProd, long idUser){

        Product p = new Product();
        p.setId(idProd);
        p.setId_user(idUser);

        return sqlProduct.select(p);
    }

}

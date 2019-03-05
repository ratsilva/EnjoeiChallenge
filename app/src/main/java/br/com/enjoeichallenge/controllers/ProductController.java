package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.User;
import br.com.enjoeichallenge.models.SQLiteManager_Photo;
import br.com.enjoeichallenge.models.SQLiteManager_Product;
import br.com.enjoeichallenge.models.SQLiteManager_User;
import br.com.enjoeichallenge.tools.restapi.HTTPRequest_Product;
import br.com.enjoeichallenge.objects.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController extends Controller {

    Context ctx;
    ArrayList<Product> listProducts;

    HTTPRequest_Product httpProduct = http.getProductService();

    SQLiteManager_Product   sqlProduct;
    SQLiteManager_Photo     sqlPhoto;
    SQLiteManager_User      sqlUser;


    public ProductController(Context ctx_){
        super(ctx_);
        ctx = ctx_;

        sqlProduct = new SQLiteManager_Product(ctx);
        sqlPhoto = new SQLiteManager_Photo(ctx);
        sqlUser = new SQLiteManager_User(ctx);
    }

    public boolean requestProductsAPI(){

        if(!testConnection()) return false;

        Call<Product.ProductJson> listProducts = httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {

            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {

                Log.v("onResponse", response.body().toString());

                // Deletar infos do BD


                // pegar a resposta
                Product.ProductJson prodJson = response.body();

                for(Product p : prodJson.getProduts()){

                    // Usuário
                    User user = p.getUser();
                    long id_user = sqlUser.save(user);

                    // Inserir produto
                    p.setId_user(id_user);
                    sqlProduct.save(p);

                }


            }

            @Override
            public void onFailure(Call<Product.ProductJson> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

        return true;

    }

    public ArrayList<Object> getListProducts(){
        return sqlProduct.selectAll("");
    }

}

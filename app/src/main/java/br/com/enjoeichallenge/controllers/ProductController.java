package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_Photo;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_Product;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_User;
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

    public void requestProducts(){

        if(!testConnection()) return;

        Call<Product.ProductJson> listProducts = httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {

            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {

                // pegar a resposta
                Product.ProductJson prodJson = response.body();

                for(Product p : prodJson.getProduts()){

                    // Inserir photo do usuário
                    long id_photo = sqlPhoto.save(p.getUser().getAvatar());

                    // Inserir usuário
                    p.getUser().setId_photo(id_photo);
                    long id_user = sqlUser.save(p.getUser());

                    // Inserir produto
                    long id_product = sqlProduct.save(p);
                    for(Photo photo : p.getPhotos()){
                        sqlPhoto.save(photo);
                    }

                    //Log.v("database", "inserindo produto: " + p.getId());

                }

            }

            @Override
            public void onFailure(Call<Product.ProductJson> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

    }

}

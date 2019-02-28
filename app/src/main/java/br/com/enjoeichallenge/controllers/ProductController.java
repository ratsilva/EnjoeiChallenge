package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.enjoeichallenge.models.Photo;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_Photo;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_Product;
import br.com.enjoeichallenge.repository.managers.SQLiteManager_User;
import br.com.enjoeichallenge.tools.restapi.HTTPRequest_Product;
import br.com.enjoeichallenge.models.Product;
import okhttp3.ResponseBody;
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
        super();
        ctx = ctx_;

        sqlProduct = new SQLiteManager_Product(ctx);
        sqlPhoto = new SQLiteManager_Photo(ctx);
        sqlUser = new SQLiteManager_User(ctx);
    }

    public void requestProducts(){

        Call<Product.ProductJson> listProducts = httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {

            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {

                // pegar a resposta
                Product.ProductJson prodJson = response.body();

                for(Product p : prodJson.getProduts()){

                    // Inserir photo do usuário
                    long id_photo = sqlPhoto.insert(p.getUser().getAvatar());

                    // Inserir usuário
                    p.getUser().setId_photo(id_photo);
                    long id_user = sqlUser.insert(p.getUser());

                    // Inserir produto
                    long id_product = sqlProduct.insert(p);
                    for(Photo photo : p.getPhotos()){
                        sqlPhoto.insert(photo);
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

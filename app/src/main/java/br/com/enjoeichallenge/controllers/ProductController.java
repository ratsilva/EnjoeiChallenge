package br.com.enjoeichallenge.controllers;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.enjoeichallenge.tools.restapi.HTTPRequest_Product;
import br.com.enjoeichallenge.models.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController extends Controller {

    ArrayList<Product> listProducts;
    HTTPRequest_Product httpProduct;

    public ProductController(){
        super();
        httpProduct = http.getProductService();
    }

    public void requestProducts(){

        Call<Product.ProductJson> listProducts = httpProduct.getProducts();

        listProducts.enqueue(new Callback<Product.ProductJson>() {
            @Override
            public void onResponse(Call<Product.ProductJson> call, Response<Product.ProductJson> response) {
                // pegar a resposta
                Product.ProductJson prodJson = response.body();

            }

            @Override
            public void onFailure(Call<Product.ProductJson> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

    }

}

package br.com.enjoeichallenge.controllers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import br.com.enjoeichallenge.api.HTTPRequest_Product;
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

        Call<ResponseBody> listProducts = httpProduct.getProducts();

        listProducts.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // pegar a resposta
                try {
                    Log.v("response", ""+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // tratar algum erro
                Log.v("failure", ""+t.getMessage());
            }
        });

    }

}

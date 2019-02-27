package br.com.enjoeichallenge.api;

import java.util.ArrayList;

import br.com.enjoeichallenge.models.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequest_Product {

    //@GET("todos/1")
    @GET("products/home")
    Call<ResponseBody> getProducts();

}

package br.com.enjoeichallenge.tools.restapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequest_Product {

    //@GET("todos/1")
    @GET("products/home")
    Call<ResponseBody> getProducts();

}

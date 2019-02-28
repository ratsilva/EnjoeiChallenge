package br.com.enjoeichallenge.tools.restapi;

import java.util.List;

import br.com.enjoeichallenge.models.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequest_Product {

    //@GET("todos/1")
    @GET("products/home")
    Call<Product.ProductJson> getProducts();

}

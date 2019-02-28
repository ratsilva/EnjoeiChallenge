package br.com.enjoeichallenge.tools.restapi;

import br.com.enjoeichallenge.objects.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HTTPRequest_Product {

    @GET("products/home")
    Call<Product.ProductJson> getProducts();

}

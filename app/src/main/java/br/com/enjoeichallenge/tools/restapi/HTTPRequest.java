package br.com.enjoeichallenge.tools.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPRequest {

    protected static String BASE_URL = "https://private-anon-1c343166a6-enjoeitest.apiary-mock.com/";

    private final Retrofit retrofit;

    public HTTPRequest(){

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public HTTPRequest_Product getProductService() {
        return this.retrofit.create(HTTPRequest_Product.class);
    }


}

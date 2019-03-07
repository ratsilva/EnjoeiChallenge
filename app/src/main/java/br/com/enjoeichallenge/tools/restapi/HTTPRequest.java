package br.com.enjoeichallenge.tools.restapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPRequest {

    protected static String BASE_URL = "https://private-anon-c1891f1f7d-enjoeitest.apiary-mock.com/";

    private final Retrofit retrofit;

    public HTTPRequest(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public HTTPRequest_Product getProductService() {
        return this.retrofit.create(HTTPRequest_Product.class);
    }


}

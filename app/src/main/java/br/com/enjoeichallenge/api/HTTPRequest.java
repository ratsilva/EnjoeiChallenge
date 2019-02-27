package br.com.enjoeichallenge.api;

import android.content.Context;

import com.google.gson.Gson;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HTTPRequest {

    protected static String BASE_URL = "https://private-anon-1c343166a6-enjoeitest.apiary-mock.com/";

    private final Retrofit retrofit;

    public HTTPRequest(){

        //BASE_URL = "https://jsonplaceholder.typicode.com/";

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

    }

    public HTTPRequest_Product getProductService() {
        return this.retrofit.create(HTTPRequest_Product.class);
    }


}

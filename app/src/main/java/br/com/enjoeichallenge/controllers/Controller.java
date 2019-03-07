package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.net.ConnectivityManager;

import br.com.enjoeichallenge.models.SQLiteManager_Photo;
import br.com.enjoeichallenge.models.SQLiteManager_Product;
import br.com.enjoeichallenge.models.SQLiteManager_ProductPhoto;
import br.com.enjoeichallenge.models.SQLiteManager_User;
import br.com.enjoeichallenge.tools.restapi.HTTPRequest;

public class Controller {

    HTTPRequest http;
    Context ctx;

    public SQLiteManager_Product sqlProduct;
    public SQLiteManager_Photo sqlPhoto;
    public SQLiteManager_User sqlUser;
    public SQLiteManager_ProductPhoto sqlProductPhoto;

    public Controller(Context ctx_){
        http = new HTTPRequest();
        ctx = ctx_;

        sqlProduct = new SQLiteManager_Product(ctx);
        sqlPhoto = new SQLiteManager_Photo(ctx);
        sqlUser = new SQLiteManager_User(ctx);
        sqlProductPhoto = new SQLiteManager_ProductPhoto(ctx);
    }

    public boolean testConnection(){

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()){
            return true;
        }

        return false;
    }

    public void deleteAllBD(){

        sqlProductPhoto.deleteAll();
        sqlPhoto.deleteAll();
        sqlUser.deleteAll();
        sqlProduct.deleteAll();

    }

}

package br.com.enjoeichallenge.controllers;

import android.content.Context;
import android.net.ConnectivityManager;

import br.com.enjoeichallenge.tools.restapi.HTTPRequest;

public class Controller {

    HTTPRequest http;
    Context ctx;

    public Controller(Context ctx_){
        http = new HTTPRequest();
        ctx = ctx_;
    }

    public boolean testConnection(){

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()){
            return true;
        }

        return false;
    }

}

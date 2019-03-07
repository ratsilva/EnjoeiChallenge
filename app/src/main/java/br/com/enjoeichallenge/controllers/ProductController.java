package br.com.enjoeichallenge.controllers;

import android.content.Context;

import java.util.ArrayList;

import br.com.enjoeichallenge.tools.restapi.HTTPRequest_Product;
import br.com.enjoeichallenge.objects.Product;

public class ProductController extends Controller {

    Context ctx;

    public HTTPRequest_Product httpProduct;

    public ProductController(Context ctx_){
        super(ctx_);
        ctx = ctx_;
        httpProduct = http.getProductService();
    }

    public ArrayList<Object> getListProducts(){
        return sqlProduct.selectAll("");
    }

    public Object getProduct(long idProd, long idUser){

        Product p = new Product();
        p.setId(idProd);
        p.setId_user(idUser);

        return sqlProduct.select(p);
    }

}

package br.com.enjoeichallenge.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.controllers.ProductController;
import br.com.enjoeichallenge.objects.Product;

public class ProductActivity extends AppCompatActivity {

    ProductController productController;

    Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        long idProd = intent.getLongExtra("idproduct", 0);
        long idUser = intent.getLongExtra("iduser", 0);

        TextView tv = findViewById(R.id.activity_product_text);
        tv.setText("IdProduct: " + idProd + " | IdUser: " + idUser);

    }

}

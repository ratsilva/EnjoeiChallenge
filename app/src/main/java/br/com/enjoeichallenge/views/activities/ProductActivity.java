package br.com.enjoeichallenge.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.controllers.ProductController;

public class ProductActivity extends AppCompatActivity {

    ProductController productController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productController = new ProductController();
        productController.requestProducts();

    }

}

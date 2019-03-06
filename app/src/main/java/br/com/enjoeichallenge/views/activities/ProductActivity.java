package br.com.enjoeichallenge.views.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rd.PageIndicatorView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ImageAdapter;
import br.com.enjoeichallenge.controllers.ProductController;
import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.tools.imageapi.ImageHelper;
import br.com.enjoeichallenge.adapters.CustomViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {

    ProductController productController;
    Product currentProduct;
    ImageHelper imgHelper;

    @BindView(R.id.activity_product_viewpager) CustomViewPager viewPager;
    @BindView(R.id.activity_product_viewpager_indicator) PageIndicatorView pageIndicatorView;

    @BindView(R.id.activity_product_txtPrice) TextView txtPrice;
    @BindView(R.id.activity_product_txtOriginalPrice) TextView txtOriginalPrice;
    @BindView(R.id.activity_product_txtMaxInstall) TextView txtMaxInstall;
    @BindView(R.id.activity_product_txtTitle) TextView txtTitle;
    @BindView(R.id.activity_product_txtContent) TextView txtContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ButterKnife.bind(this);

        // Initialize variables
        imgHelper = new ImageHelper(this);
        productController = new ProductController(this);

        // Load current Product Detail
        loadCurrentProduct();

        //
        setupViewPager(viewPager);

    }

    private void loadCurrentProduct(){

        Intent intent = getIntent();
        long idProd = intent.getLongExtra("idproduct", 0);
        long idUser = intent.getLongExtra("iduser", 0);

        currentProduct = (Product) productController.getProduct(idProd, idUser);

        setupFields();

    }

    private void setupViewPager(ViewPager viewPager) {

        ImageAdapter adapter = new ImageAdapter(this);

        for(Photo currentPhoto : currentProduct.getPhotos()){

            String urlPhoto = imgHelper.getImageURL(currentPhoto.getPublic_id(), currentPhoto.getCrop(), currentPhoto.getGravity(), 400, 600, false);
            adapter.addImage(urlPhoto);

        }

        viewPager.setAdapter(adapter);

        pageIndicatorView.setCount(currentProduct.getPhotos().size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {/*empty*/}
        });
    }

    private void setupFields(){

        txtPrice            .setText("R$ " + (int) currentProduct.getPrice());

        String discount = "";
        if(currentProduct.getDiscount_percentage() > 0){
            discount = (int) currentProduct.getDiscount_percentage() + "% off ";

            txtOriginalPrice    .setVisibility(View.VISIBLE);
            txtOriginalPrice    .setText("R$ " + (int) currentProduct.getOriginal_price());
            txtOriginalPrice    .setPaintFlags(txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        if(currentProduct.getMaximum_installment() > 0){
            txtMaxInstall       .setVisibility(View.VISIBLE);
            txtMaxInstall       .setText(discount + "em até " + currentProduct.getMaximum_installment() + "x");
        }

        txtTitle            .setText(currentProduct.getTitle());
        txtContent          .setText(currentProduct.getContent());

    }
}

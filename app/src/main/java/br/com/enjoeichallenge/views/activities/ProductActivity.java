package br.com.enjoeichallenge.views.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.rd.PageIndicatorView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ImageAdapter;
import br.com.enjoeichallenge.controllers.ProductController;
import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.tools.imageapi.ImageHelper;
import br.com.enjoeichallenge.views.widgets.CustomViewPager;
import br.com.enjoeichallenge.views.widgets.ImageCircleView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {

    ProductController productController;
    Product currentProduct;
    ImageHelper imgHelper;

    @BindView(R.id.activity_product_viewpager) CustomViewPager viewPager;
    @BindView(R.id.activity_product_viewpager_indicator) PageIndicatorView pageIndicatorView;

    @BindView(R.id.activity_product_toolbar) Toolbar toolBar;
    @BindView(R.id.activity_product_appBar) AppBarLayout appBar;

    @BindView(R.id.activity_product_txtPrice) TextView txtPrice;
    @BindView(R.id.activity_product_txtOriginalPrice) TextView txtOriginalPrice;
    @BindView(R.id.activity_product_txtMaxInstall) TextView txtMaxInstall;
    @BindView(R.id.activity_product_txtTitle) TextView txtTitle;
    @BindView(R.id.activity_product_txtContent) TextView txtContent;
    @BindView(R.id.activity_product_imgComment) ImageCircleView imgComment;
    @BindView(R.id.activity_product_imgLike) ImageCircleView imgLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ButterKnife.bind(this);

        // Setup AppBar and ToolBar
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        appBar.bringToFront();

        // Initialize variables
        imgHelper = new ImageHelper(this);
        productController = new ProductController(this);

        // Load current Product Detail
        loadCurrentProduct();

        // Setup ViewPager
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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) { pageIndicatorView.setSelection(position);}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void setupFields(){

        txtPrice            .setText("" + (int) currentProduct.getPrice());

        String discount = "";
        if(currentProduct.getDiscount_percentage() > 0){
            discount = (int) currentProduct.getDiscount_percentage() + "% off ";

            txtOriginalPrice    .setVisibility(View.VISIBLE);
            txtOriginalPrice    .setText("R$ " + (int) currentProduct.getOriginal_price());
            txtOriginalPrice    .setPaintFlags(txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        if(currentProduct.getMaximum_installment() > 0){
            txtMaxInstall       .setText(discount + "em até " + currentProduct.getMaximum_installment() + "x");
        }else{
            txtMaxInstall       .setText("à vista");
        }

        txtTitle            .setText(currentProduct.getTitle());
        txtContent          .setText(currentProduct.getContent());

        String strLikes = null;
        if(currentProduct.getLikes_count() > 0){
            strLikes = "" + currentProduct.getLikes_count();
        }

        imgLike.setText(strLikes);
        imgLike.setImage(R.drawable.ic_like);

        String strComments = null;
        if(currentProduct.getPublished_comments_count() > 0){
            strComments = "" + currentProduct.getPublished_comments_count();
        }
        imgComment.setText(strComments);
        imgComment.setImage(R.drawable.ic_comment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_item_one:
                showMessage("Clicou no item 1");
                return true;
            case R.id.action_item_two:
                showMessage("Clicou no item 2");
                return true;
            case R.id.action_item_three:
                showMessage("Clicou no item 3");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}

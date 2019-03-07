package br.com.enjoeichallenge.views.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {

    // Variáveis auxiliares de funcionamento
    ProductController productController;
    Product currentProduct;
    ImageHelper imgHelper;

    // Referenciando variáveis com o layout através da biblioteca ButterKnife
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
    @BindView(R.id.activity_product_btn_back) ImageView btnBack;
    @BindView(R.id.activity_product_btn_share) ImageView btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Bind activity com ButterKnife
        ButterKnife.bind(this);

        // Adicionando toolBar
        setSupportActionBar(toolBar);

        // Removendo titulo da appBar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Trazendo a appBar para primeiro plano
        appBar.bringToFront();

        // Inicializando auxiliares de funcionamento
        imgHelper = new ImageHelper(this);
        productController = new ProductController(this);

        // Carrega produto atual passado por parâmetro
        loadCurrentProduct();

        // Configurando viewPager
        setupViewPager(viewPager);

        // Configurando viewPagerIndicator
        setupViewPagerIndicator(viewPager);

    }

    /**
     *  Evento do botão voltar
     *  Fecha a activity atual
     */
    @OnClick(R.id.activity_product_btn_back)
    public void backButton(){
        finish();
    }

    /**
     *  Evento do botão compartilhar
     *  Exibe uma mensagem com Toast
     */
    @OnClick(R.id.activity_product_btn_share)
    public void shareButton(){
        showMessage("Clicou em Compartilhar Produto");
    }

    /**
     *  Recebe o viewPager como parametro
     *  Cria um adapter
     *  Adiciona a URL das imagens no adapter
     *  Adiciona adapter no viewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {

        ImageAdapter adapter = new ImageAdapter(this);

        for(Photo currentPhoto : currentProduct.getPhotos()){

            String urlPhoto = imgHelper.getImageURL(currentPhoto.getPublic_id(), currentPhoto.getCrop(), currentPhoto.getGravity(), 400, 600, false);
            adapter.addImage(urlPhoto);

        }

        viewPager.setAdapter(adapter);

    }

    /**
     *  Recebe o viewPager como parametro
     *  Define a quantidade de fotos para o viewPagerIndicator
     *  Implementa o método onPageSelected para indicar a foto atual
     * @param viewPager
     */
    private void setupViewPagerIndicator(ViewPager viewPager){

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

    /**
     *  Carrega o produto atual (selecionado na tela de listagem)
     *  Carrega as informações do produto na tela com o método setupFields()
     */
    private void loadCurrentProduct(){

        // Pega os parâmetros da activity (idproduct e iduser)
        Intent intent = getIntent();
        long idProd = intent.getLongExtra("idproduct", 0);
        long idUser = intent.getLongExtra("iduser", 0);

        // Busca o produto no banco de dados e define no currentProduct
        currentProduct = (Product) productController.getProduct(idProd, idUser);

        // Carrega informações do produto na tela
        setupFields();

    }

    /**
     *  Carrega as informações do produto atual na tela
     */
    private void setupFields(){

        // Define preço
        txtPrice            .setText("" + (int) currentProduct.getPrice());

        // Define preço original
        String discount = "";
        if(currentProduct.getDiscount_percentage() > 0){
            discount = (int) currentProduct.getDiscount_percentage() + "% off ";

            txtOriginalPrice    .setVisibility(View.VISIBLE);
            txtOriginalPrice    .setText("R$ " + (int) currentProduct.getOriginal_price());
            txtOriginalPrice    .setPaintFlags(txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        // Define condições de pagamento
        if(currentProduct.getMaximum_installment() > 0){
            txtMaxInstall       .setText(discount + "em até " + currentProduct.getMaximum_installment() + "x");
        }else{
            txtMaxInstall       .setText("à vista");
        }

        // Define título
        txtTitle            .setText(currentProduct.getTitle());

        // Define descrição
        txtContent          .setText(currentProduct.getContent());

        String strLikes = null;
        if(currentProduct.getLikes_count() > 0){
            strLikes = "" + currentProduct.getLikes_count();
        }

        // Define qtd de likes
        imgLike.setText(strLikes);

        // Define ícone de likes
        imgLike.setImage(R.drawable.ic_like);

        String strComments = null;
        if(currentProduct.getPublished_comments_count() > 0){
            strComments = "" + currentProduct.getPublished_comments_count();
        }

        // Define qtd de comentários
        imgComment.setText(strComments);

        // Define ícone de comentários
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

    /**
     * Recebe uma string como parâmetro e exibe um Toast com ela
     * @param message
     */
    private void showMessage(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}

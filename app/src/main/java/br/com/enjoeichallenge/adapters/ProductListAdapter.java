package br.com.enjoeichallenge.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.tools.imageapi.ImageHelper;
import br.com.enjoeichallenge.views.activities.ProductActivity;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> productList;
    private Context ctx;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private class ProductItemHolder extends RecyclerView.ViewHolder {
        private TextView discountPorc, title, price, size, likes;
        private ImageView productPhoto, userPhoto, likesPhoto;
        private ImageHelper imgHelper;

        private ProductItemHolder(View view) {
            super(view);

            imgHelper = new ImageHelper(ctx);

            discountPorc    = (TextView) view.findViewById(R.id.fragment_productlist_card_discountPorc);
            title           = (TextView) view.findViewById(R.id.fragment_productlist_card_title);
            price           = (TextView) view.findViewById(R.id.fragment_productlist_card_price);
            size            = (TextView) view.findViewById(R.id.fragment_productlist_card_size);
            likes           = (TextView) view.findViewById(R.id.fragment_productlist_card_likes);

            productPhoto    = (ImageView) view.findViewById(R.id.fragment_productlist_card_productPhoto);
            userPhoto       = (ImageView) view.findViewById(R.id.fragment_productlist_card_userPhoto);
            likesPhoto      = (ImageView) view.findViewById(R.id.fragment_productlist_card_likesPhoto);
        }
    }

    private class HeaderItemHolder extends RecyclerView.ViewHolder{

        private HeaderItemHolder(View view) {
            super(view);
        }
    }

    public ProductListAdapter(ArrayList<Object> productList_, Context ctx_) {
        this.productList = productList_;
        this.ctx = ctx_;
    }

    public void refreshList(ArrayList<Object> productList_){
        this.productList.clear();
        this.productList.addAll(productList_);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_productlist_header, parent, false);
            return new HeaderItemHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_productlist_card, parent, false);
            return new ProductItemHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ProductItemHolder){

            Product currentProduct = (Product) productList.get(position-1);

            if(currentProduct.getDiscount_percentage() > 0){
                ((ProductItemHolder) holder).discountPorc.setVisibility(View.VISIBLE);
                ((ProductItemHolder) holder).discountPorc.setText("-" + (int) currentProduct.getDiscount_percentage() + "%");
            }

            ((ProductItemHolder) holder).title.setText(currentProduct.getTitle());

            ((ProductItemHolder) holder).price.setText("R$ " + (int) currentProduct.getPrice());

            if(currentProduct.getSize() != null){
                ((ProductItemHolder) holder).size.setVisibility(View.VISIBLE);
                ((ProductItemHolder) holder).size.setText(" - tam " + currentProduct.getSize());
            }

            ((ProductItemHolder) holder).likes.setText("" + currentProduct.getLikes_count());

            if(currentProduct.getPhotos().size() > 0){
                Photo productPhoto = currentProduct.getPhotos().get(0);
                String urlPhotoOne = ((ProductItemHolder) holder).imgHelper.getImageURL(productPhoto.getPublic_id(), productPhoto.getCrop(), productPhoto.getGravity(), 350, 500, false);
                ((ProductItemHolder) holder).imgHelper.loadImage(urlPhotoOne, ((ProductItemHolder) holder).productPhoto);
            }

            Photo userPhoto = currentProduct.getUser().getAvatar();
            String urlUserPhoto = ((ProductItemHolder) holder).imgHelper.getImageURL(userPhoto.getPublic_id(), userPhoto.getCrop(), userPhoto.getGravity(), 90, 90, true);
            ((ProductItemHolder) holder).imgHelper.loadImage(urlUserPhoto, ((ProductItemHolder) holder).userPhoto);

            ((ProductItemHolder) holder).likesPhoto.setSelected(false);
            ((ProductItemHolder) holder).likes.setTextColor(ctx.getResources().getColor(R.color.cinza));

            ((ProductItemHolder) holder).likesPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(((ProductItemHolder) holder).likesPhoto.isSelected()){
                        currentProduct.setLikes_count(currentProduct.getLikes_count()-1);
                        ((ProductItemHolder) holder).likes.setText("" + currentProduct.getLikes_count());
                        ((ProductItemHolder) holder).likes.setTextColor(ctx.getResources().getColor(R.color.cinza));
                        ((ProductItemHolder) holder).likesPhoto.setSelected(false);

                    }else{
                        currentProduct.setLikes_count(currentProduct.getLikes_count()+1);
                        ((ProductItemHolder) holder).likes.setText("" + currentProduct.getLikes_count());
                        ((ProductItemHolder) holder).likes.setTextColor(ctx.getResources().getColor(R.color.rosa));
                        ((ProductItemHolder) holder).likesPhoto.setSelected(true);
                    }


                }
            });

            ((ProductItemHolder) holder).productPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(ctx, ProductActivity.class);
                    i.putExtra("idproduct", currentProduct.getId());
                    i.putExtra("iduser", currentProduct.getId_user());
                    ctx.startActivity(i);

                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return productList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    public boolean isPositionHeader(int position) {
        return position == 0;
    }
}

package br.com.enjoeichallenge.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.tools.imageapi.ImageHelper;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductItemHolder> {

    private ArrayList<Object> productList;
    private Context ctx;

    public class ProductItemHolder extends RecyclerView.ViewHolder {
        public TextView discountPorc, title, price, size, likes;
        public ImageView productPhoto, userPhoto, likesPhoto;
        public ImageHelper imgHelper;

        public ProductItemHolder(View view) {
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

    public ProductListAdapter(ArrayList<Object> productList_, Context ctx_) {
        this.productList = productList_;
        this.ctx = ctx_;
    }

    @NonNull
    @Override
    public ProductItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_productlist_card, parent, false);
        return new ProductItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemHolder holder, int position) {

        Product currentProduct = (Product) productList.get(position);

        if(currentProduct.getDiscount_percentage() > 0){
            holder.discountPorc     .setVisibility(View.VISIBLE);
            holder.discountPorc     .setText("-" + (int) currentProduct.getDiscount_percentage() + "%");
        }

        holder.title            .setText(currentProduct.getTitle());

        holder.price            .setText("R$ " + (int) currentProduct.getPrice());

        if(currentProduct.getSize() != null){
            holder.size             .setVisibility(View.VISIBLE);
            holder.size             .setText(" - tam " + currentProduct.getSize());
        }

        holder.likes            .setText("" + currentProduct.getLikes_count());

        Photo productPhoto = currentProduct.getPhotos().get(0);
        String urlPhotoOne = holder.imgHelper.getImageURL(productPhoto.getPublic_id(), productPhoto.getCrop(), productPhoto.getGravity(), 350, 500, false);
        holder.imgHelper.loadImage(urlPhotoOne, R.drawable.progress_image, holder.productPhoto);

        Photo userPhoto = currentProduct.getUser().getAvatar();
        String urlUserPhoto = holder.imgHelper.getImageURL(userPhoto.getPublic_id(), userPhoto.getCrop(), userPhoto.getGravity(), 90, 90, true);
        holder.imgHelper.loadImage(urlUserPhoto, R.drawable.progress_image, holder.userPhoto);

        holder.likesPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.likesPhoto.isSelected()){
                    currentProduct.setLikes_count(currentProduct.getLikes_count()-1);
                    holder.likes.setText("" + currentProduct.getLikes_count());
                    holder.likes.setTextColor(ctx.getResources().getColor(R.color.cinza));
                    holder.likesPhoto.setSelected(false);

                }else{
                    currentProduct.setLikes_count(currentProduct.getLikes_count()+1);
                    holder.likes.setText("" + currentProduct.getLikes_count());
                    holder.likes.setTextColor(ctx.getResources().getColor(R.color.rosa));
                    holder.likesPhoto.setSelected(true);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}

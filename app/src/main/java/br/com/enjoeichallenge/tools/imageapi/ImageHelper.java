package br.com.enjoeichallenge.tools.imageapi;

import android.content.Context;
import android.widget.ImageView;

import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

import br.com.enjoeichallenge.R;

public class ImageHelper {

    static Context ctx;

    public ImageHelper(Context ctx_){
        this.ctx = ctx_;
    }

    public String getImageURL(String public_id, String crop, String gravity, int width, int height, boolean user){

        Transformation tr = new Transformation();
        tr.crop(crop);
        tr.gravity(gravity);

        if(width>0)tr.width(width);
        if(height>0)tr.height(height);

        if(user){
            tr.radius("max");
        }

        String url = MediaManager.get().url().transformation(tr).generate(public_id + ".jpg");

        return url;

    }

    public static void loadImage(String imageUrl, ImageView imageView) {

        Picasso.with(ctx)
                .load(imageUrl)
                .noFade()
                .placeholder(R.drawable.progress_animation)
                .into(imageView);

    }

}

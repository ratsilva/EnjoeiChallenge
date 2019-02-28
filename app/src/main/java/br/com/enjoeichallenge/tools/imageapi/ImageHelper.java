package br.com.enjoeichallenge.tools.imageapi;

import android.content.Context;
import android.widget.ImageView;

import com.cloudinary.Transformation;
import com.cloudinary.android.MediaManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ImageHelper {

    Context ctx;

    public ImageHelper(Context ctx_){
        this.ctx = ctx_;
    }

    public String getImageURL(String public_id, String crop, String gravity){

        Map config = new HashMap();
        config.put("cloud_name", "demo");
        MediaManager.init(ctx, config);


        Transformation tr = new Transformation();
        tr.crop(crop);
        tr.gravity(gravity);
        //tr.width();
        //tr.height();

        String url = MediaManager.get().url().transformation(tr).generate(public_id + ".jpg");

        return url;

    }

    public void loadImage(String imageUrl, int placeHolderResourceId, ImageView imageView) {
        Picasso.with(ctx)
                .load(imageUrl)
                .placeholder(placeHolderResourceId)
                .into(imageView);
    }

}

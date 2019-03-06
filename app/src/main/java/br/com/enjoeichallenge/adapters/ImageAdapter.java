package br.com.enjoeichallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.tools.imageapi.ImageHelper;

public class ImageAdapter extends PagerAdapter {
    private final List<String> mImageList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context ctx;
    private ImageHelper imgHelper;

    public ImageAdapter(Context ctx_){
        this.ctx = ctx_;
        this.mLayoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imgHelper = new ImageHelper(ctx);
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.activity_product_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.activity_product_pager_img);
        imgHelper.loadImage(mImageList.get(position), imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    public void addImage(String imageURL){
        mImageList.add(imageURL);
    }

}
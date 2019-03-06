package br.com.enjoeichallenge.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import br.com.enjoeichallenge.R;

public class ImageCircleView extends LinearLayout {

    private String text;
    private int image;

    public ImageCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.view_imagecircle, this);
        this.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ImageView imgView = (ImageView) findViewById(R.id.view_imagecircle_img);
        imgView.setImageResource(image);

        TextCircleView txtView = (TextCircleView) findViewById(R.id.view_imagecircle_txt);
        if(text != null){
            txtView.setText(text);
        }else{
            txtView.setVisibility(View.GONE);
        }

        super.onDraw(canvas);
    }

    public void setText(String text_) {
        this.text = text_;
    }

    public void setImage(int image_) {
        this.image = image_;
    }
}

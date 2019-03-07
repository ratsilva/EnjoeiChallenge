package br.com.enjoeichallenge.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import br.com.enjoeichallenge.R;

public class ImageCircleView extends LinearLayout {

    private String text;
    private int textVisibility;
    private int image;
    private boolean imagePressed;

    public ImageCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.view_imagecircle, this);
        this.setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ImageView imgView = (ImageView) findViewById(R.id.view_imagecircle_img);
        imgView.setImageResource(image);
        imgView.setSelected(imagePressed);

        TextCircleView txtView = (TextCircleView) findViewById(R.id.view_imagecircle_txt);
        txtView.setText(text);
        txtView.setVisibility(textVisibility);

        super.onDraw(canvas);
    }

    public void setText(String text_) {

        if(text_.equals("0")){
            this.textVisibility = View.GONE;
            this.text = "";
        }else{
            this.textVisibility = View.VISIBLE;
            this.text = text_;
        }
    }

    public void setImage(int image_) {
        this.image = image_;
    }

    public void setImagePressed(boolean imagePressed_){
        this.imagePressed = imagePressed_;
    }

    public boolean isImagePressed(){
        return this.imagePressed;
    }
}

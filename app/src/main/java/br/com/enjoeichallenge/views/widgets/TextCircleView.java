package br.com.enjoeichallenge.views.widgets;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import br.com.enjoeichallenge.R;

public class TextCircleView extends AppCompatTextView {

    int solidColor = R.color.rosa;

    public TextCircleView(Context context) {
        super(context);
    }

    public TextCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(solidColor));
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        int  h = this.getHeight();
        int  w = this.getWidth();

        int diameter = ((h > w) ? h : w);
        int radius = diameter/2;

        this.setHeight(diameter);
        this.setWidth(diameter);

        canvas.drawCircle(diameter / 2, diameter / 2, radius, circlePaint);

        super.draw(canvas);
    }

}
package br.com.enjoeichallenge.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import br.com.enjoeichallenge.R;

public class ErrorView extends LinearLayout {

    public ErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_error, this);
    }
}

package br.com.enjoeichallenge.views.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.enjoeichallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ErrorFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.fragment_error_btn) Button btnError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_error, container, false);
        unbinder = ButterKnife.bind(this, view);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "font/proximanovasemibold.otf");
        btnError.setTypeface(font);

        return view;
    }

    @OnClick(R.id.fragment_error_btn)
    public void onClickErrorBtn(){

        // Simular click no item ativo tabbar

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

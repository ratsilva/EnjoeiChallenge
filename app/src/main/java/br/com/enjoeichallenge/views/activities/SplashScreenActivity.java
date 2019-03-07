package br.com.enjoeichallenge.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import br.com.enjoeichallenge.R;

public class SplashScreenActivity extends AppCompatActivity {

    // Tempo de espera até abrir a próxima actiivty
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Context ctx = this;

        // Inicia o handler com o tempo definido para trocar de acitivity
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                // Finaliza activity atual e abre a MainActivity
                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
                finish();

            }

        }, SPLASH_TIME_OUT);

    }

}

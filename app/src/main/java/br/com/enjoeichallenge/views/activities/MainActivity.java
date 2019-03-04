package br.com.enjoeichallenge.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.views.fragments.MainFragment;

public class MainActivity extends FragmentActivity {

    private final int PRODUCTLIST_FRAGMENT  = 0;
    private final int SEARCH_FRAGMENT       = 1;
    private final int CAMERA_FRAGMENT       = 2;
    private final int INBOX_FRAGMENT        = 3;
    private final int USER_FRAGMENT         = 4;
    private final int ERROR_FRAGMENT        = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabbar


        // Controle de fragment
        //carregaFragment(PRODUCTLIST_FRAGMENT);
        //Verificar se precisa abrir o fragment de conexão
    }

    /**
     * Recebe o fragment desejado e carrega no FrameLayout da Activity
     * @param position
     */
    private void carregaFragment(int position){

        Fragment fragment = null;

        // Define o fragment que será carregado
        // Se o position passado for inválido, abre o fragment de PRODUCTLIST como default
        switch (position){
            case PRODUCTLIST_FRAGMENT:
                fragment = new MainFragment();
                break;
            case SEARCH_FRAGMENT:
                fragment = new MainFragment();
                break;
            case CAMERA_FRAGMENT:
                fragment = new MainFragment();
                break;
            case INBOX_FRAGMENT:
                fragment = new MainFragment();
                break;
            case USER_FRAGMENT:
                fragment = new MainFragment();
                break;
            case ERROR_FRAGMENT:
                fragment = new MainFragment();
                break;
            default:
                fragment = new MainFragment();
                break;
        }

        if (fragment != null) {

            // Carrega o fragment selecionado no FrameLayout
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_main_fragment, fragment).commit();

        }

    }

}

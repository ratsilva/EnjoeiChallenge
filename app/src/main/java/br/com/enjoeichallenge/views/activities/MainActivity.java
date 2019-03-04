package br.com.enjoeichallenge.views.activities;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.views.fragments.CameraFragment;
import br.com.enjoeichallenge.views.fragments.ErrorFragment;
import br.com.enjoeichallenge.views.fragments.MailFragment;
import br.com.enjoeichallenge.views.fragments.MainFragment;
import br.com.enjoeichallenge.views.fragments.ProductListFragment;
import br.com.enjoeichallenge.views.fragments.SearchFragment;
import br.com.enjoeichallenge.views.fragments.UserFragment;

public class MainActivity extends FragmentActivity {

    private final int PRODUCTLIST_FRAGMENT  = 0;
    private final int SEARCH_FRAGMENT       = 1;
    private final int CAMERA_FRAGMENT       = 2;
    private final int MAIL_FRAGMENT         = 3;
    private final int USER_FRAGMENT         = 4;
    private final int ERROR_FRAGMENT        = 5;

    private int ACTIVE_FRAGMENT;

    public static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;

        // Tabbar


    }

    public static void chooseActiveFragment(){



    }

    /**
     * Recebe o fragment desejado e carrega no FrameLayout da Activity
     * @param fragmentOption
     */
    private void commitFragment(int fragmentOption){

        Fragment fragment = null;

        // Define o fragment que será carregado
        // Se o position passado for inválido, abre o fragment de PRODUCTLIST como default
        switch (fragmentOption){
            case PRODUCTLIST_FRAGMENT:
                fragment = new ProductListFragment();
                break;
            case SEARCH_FRAGMENT:
                fragment = new SearchFragment();
                break;
            case CAMERA_FRAGMENT:
                fragment = new CameraFragment();
                break;
            case MAIL_FRAGMENT:
                fragment = new MailFragment();
                break;
            case USER_FRAGMENT:
                fragment = new UserFragment();
                break;
            case ERROR_FRAGMENT:
                fragment = new ErrorFragment();
                break;
            default:
                fragment = new ProductListFragment();
                break;
        }

        if (fragment != null) {

            // Carrega o fragment selecionado no FrameLayout
            ACTIVE_FRAGMENT = fragmentOption;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_main_fragment, fragment).commit();

        }

    }

}

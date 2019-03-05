package br.com.enjoeichallenge.views.activities;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ViewPagerAdapter;
import br.com.enjoeichallenge.views.fragments.CameraFragment;
import br.com.enjoeichallenge.views.fragments.ErrorFragment;
import br.com.enjoeichallenge.views.fragments.MailFragment;
import br.com.enjoeichallenge.views.fragments.ProductListFragment;
import br.com.enjoeichallenge.views.fragments.SearchFragment;
import br.com.enjoeichallenge.views.fragments.UserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final int PRODUCTLIST_FRAGMENT  = 0;
    private final int SEARCH_FRAGMENT       = 1;
    private final int CAMERA_FRAGMENT       = 2;
    private final int MAIL_FRAGMENT         = 3;
    private final int USER_FRAGMENT         = 4;
    private final int ERROR_FRAGMENT        = 5;

    private int ACTIVE_FRAGMENT;

    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_search,
            R.drawable.ic_camera,
            R.drawable.ic_mail,
            R.drawable.ic_user
    };

    @BindView(R.id.activity_main_tabs) TabLayout tabLayout;
    @BindView(R.id.activity_main_viewpager) ViewPager viewPager;
    @BindView(R.id.activity_main_fragment_error) LinearLayout fragmentError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        defineTabIcons();

        defineErrorFragment(false);

    }

    public void defineErrorFragment(boolean error){

        // true - Error
        // false - Normal
        if(error) {
            viewPager.setVisibility(View.GONE);
            fragmentError.setVisibility(View.VISIBLE);
        }else{
            viewPager.setVisibility(View.VISIBLE);
            fragmentError.setVisibility(View.GONE);
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductListFragment(), "ProductList");
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new CameraFragment(), "Camera");
        adapter.addFragment(new MailFragment(), "Mail");
        adapter.addFragment(new UserFragment(), "User");

        viewPager.setAdapter(adapter);
    }

    private void defineTabIcons() {

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }


    /**
     * Recebe o fragment desejado e carrega no FrameLayout da Activity
     * @param fragmentOption
     */
    /*
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
    */

}

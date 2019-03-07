package br.com.enjoeichallenge.views.activities;

import android.os.Bundle;
import android.util.Log;

import com.cloudinary.android.MediaManager;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import br.com.enjoeichallenge.R;
import br.com.enjoeichallenge.adapters.ViewPagerAdapter;
import br.com.enjoeichallenge.views.fragments.CameraFragment;
import br.com.enjoeichallenge.views.fragments.MailFragment;
import br.com.enjoeichallenge.views.fragments.ProductListFragment;
import br.com.enjoeichallenge.views.fragments.SearchFragment;
import br.com.enjoeichallenge.views.fragments.UserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Icones utilizados na TabLayout
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_search,
            R.drawable.ic_camera,
            R.drawable.ic_mail,
            R.drawable.ic_user
    };

    // Referenciando variáveis com o layout através da biblioteca ButterKnife
    @BindView(R.id.activity_main_tabs) TabLayout tabLayout;
    @BindView(R.id.activity_main_viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind activity com ButterKnife
        ButterKnife.bind(this);

        // Adicionando fragments ao viewPager
        setupViewPager(viewPager);

        // Adiciona viewPager no TabLayout
        tabLayout.setupWithViewPager(viewPager);

        // Configurando ícones da TabLayout
        defineTabIcons();

        // Configurações para utilização do Cloudinary
        Map config = new HashMap();
        config.put("cloud_name", "demo");

        // Inicialização do Cloudinary
        try {
            MediaManager.init(this, config);
        }catch (RuntimeException e){
            if(e.getMessage().contains("MediaManager is already initialized")){
                Log.v("cloudinary", "MediaManager já inicializado");
            }
        }

    }

    /**
     * Recebe o viewPager
     * Cria um adapter
     * Adiciona os fragments no adapter
     * Adiciona o adapter no viewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProductListFragment(), "ProductList");
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new CameraFragment(), "Camera");
        adapter.addFragment(new MailFragment(), "Mail");
        adapter.addFragment(new UserFragment(), "User");

        viewPager.setAdapter(adapter);

    }

    /**
     * Adiciona ícones na tabbar nas posições corretas
     */
    private void defineTabIcons() {

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

    }

}

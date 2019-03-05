package br.com.enjoeichallenge.views.activities;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.cloudinary.android.MediaManager;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

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
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_search,
            R.drawable.ic_camera,
            R.drawable.ic_mail,
            R.drawable.ic_user
    };

    @BindView(R.id.activity_main_tabs) TabLayout tabLayout;
    @BindView(R.id.activity_main_viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        defineTabIcons();

        Map config = new HashMap();
        config.put("cloud_name", "demo");

        try {
            MediaManager.init(this, config);
        }catch (RuntimeException e){
            if(e.getMessage().contains("MediaManager is already initialized")){
                Log.v("cloudinary", "MediaManager já inicializado");
            }
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

}

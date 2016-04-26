package acm1pt.badassapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class home extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        assert viewPager != null;
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager(),home.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        assert tabLayout != null;
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_equalizer_white_48dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_silverware_variant));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_hotel));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle("Estatus");
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle("Despensa");
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle("Habitaciones");
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        toolbar.setTitle("Configuración");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

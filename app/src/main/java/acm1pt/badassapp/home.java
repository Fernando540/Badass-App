package acm1pt.badassapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.view.View;
import android.widget.Switch;


public class home extends AppCompatActivity{
    Switch en1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        Intent intent = getIntent();
        final String tipoUsr = intent.getStringExtra("tipoUsr");

        /*--------TOAST PARA VERIFICAR TIPOUSR
        Context context = getApplicationContext();
        CharSequence text = tipoUsr;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

        final Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        assert viewPager != null;
        if(tipoUsr.equals("1")){
            viewPager.setAdapter(new FragPagerAdapt1(getSupportFragmentManager(),home.this));
        }else{
            viewPager.setAdapter(new FragPagerAdapt2(getSupportFragmentManager(),home.this));
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        assert tabLayout != null;
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        if(tipoUsr.equals("1")){
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_equalizer_white_48dp));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_silverware_variant));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_hotel));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings));
        }else{
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_equalizer_white_48dp));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_hotel));
            tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings));
        }


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tipoUsr.equals("1")){
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
                }else{
                    switch(tab.getPosition()) {
                        case 0:
                            viewPager.setCurrentItem(0);
                            toolbar.setTitle("Estatus");
                            break;
                        case 1:
                            viewPager.setCurrentItem(1);
                            toolbar.setTitle("Habitaciones");
                            break;
                        case 2:
                            viewPager.setCurrentItem(2);
                            toolbar.setTitle("Configuración");
                            break;
                    }
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

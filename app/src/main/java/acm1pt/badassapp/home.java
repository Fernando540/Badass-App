package acm1pt.badassapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;


public class home extends AppCompatActivity implements View.OnClickListener{
    Switch boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        Intent intent=new Intent();
        final String tipoUsr=intent.getStringExtra("tipoUsr");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        boton=(Switch) findViewById(R.id.enchufe1);
        boton.setOnClickListener(this);
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
                            viewPager.setCurrentItem(2);
                            toolbar.setTitle("Habitaciones");
                            break;
                        case 2:
                            viewPager.setCurrentItem(3);
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

    @Override
    public void onClick(View v) {
        if(v==boton){
            String correo="e@e.com";
            String enchufe=boton.getText().toString();
            String habit="Habitacion 1";
            String voltaje="";
            if(boton.isChecked()){
                voltaje="0";
            }else{
                voltaje="50";
            }
            WebService.onOff(correo,voltaje,enchufe,habit);
        }
    }
}

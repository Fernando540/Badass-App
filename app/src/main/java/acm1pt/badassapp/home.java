package acm1pt.badassapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.ParameterizedType;

public class home extends AppCompatActivity{
    ViewPager pager;
    TabLayout tabLayout;
    PagerAdapter adapter;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);


        manager=getSupportFragmentManager();




        //adapter=new PagerAdapter(manager);


        pager.setAdapter(adapter);


        tabLayout.setupWithViewPager(pager);


        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setTabsFromPagerAdapter(adapter);

    }
}

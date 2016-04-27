package acm1pt.badassapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ferna on 26/04/2016.
 */
public class FragPagerAdapt2 extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = {"Estatus","Habitaciones","Configuración"};

    private Context context;

    public FragPagerAdapt2(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch(position) {
            case 0:f = EstatusFragment.newInstance();
                break;
            case 1:f = RoomsFragment.newInstance();
                break;
            case 2:f = ConfigFragment.newInstance();
                break;
        }

        return f;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}


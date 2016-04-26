package acm1pt.badassapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = {"Estatus", "DESPENSA","Habitaciones","Configuración"};

    private Context context;

    public MiFragmentPagerAdapter(FragmentManager fm, Context context) {
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
            case 1:f = DespensaFragment.newInstance();
                break;
            case 2:f = RoomsFragment.newInstance();
                break;
            case 3:f = ConfigFragment.newInstance();
                break;
        }

        return f;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

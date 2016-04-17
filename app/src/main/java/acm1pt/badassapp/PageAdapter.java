package acm1pt.badassapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Enriq on 16/04/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag=new EstatusFragment();
                break;
            case 1:
                frag=new DespensaFragment();
                break;
            /*case 2:
                frag=new StudyFragment();
                break;*/
        }
        return frag;

    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Estatus";
                break;
            case 1:
                title="Despensa";
                break;
            /*case 2:
                title="Study";
                break;*/
        }

        return title;
    }
}

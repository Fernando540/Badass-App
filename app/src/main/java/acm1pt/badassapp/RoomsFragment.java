package acm1pt.badassapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


public class RoomsFragment extends Fragment  {
    static Switch boton;
    public static RoomsFragment newInstance() {
        RoomsFragment fragment = new RoomsFragment();
        return fragment;
    }

    public RoomsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_rooms, container, false);
        boton=(Switch) v.findViewById(R.id.enchufe1);
        boton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    PrendeTask taskOn = new PrendeTask();
                    taskOn.execute();
                }else{
                    ApagaTask taskOff = new ApagaTask();
                    taskOff.execute();
                }
            }
        });
        return v;
    }

    public void setUse(String uso){

    }

    private class PrendeTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            WebService.onOff("e@e.com","50","Enchufe1","Habitación 1");
            return null;
        }
    }

    private class ApagaTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            WebService.onOff("e@e.com","0","Enchufe1","Habitación 1");
            return null;
        }
    }


}

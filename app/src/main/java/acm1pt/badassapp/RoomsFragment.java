package acm1pt.badassapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class RoomsFragment extends Fragment implements View.OnClickListener {
    Switch boton;
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
        boton.setOnClickListener(this);
        return v;
    }
    public void apagaPrende1(String voltaje, String contacto){

    }

    @Override
    public void onClick(View v) {
        if(v==boton){
            String correo="e@e.com";
            String enchufe="Enchufe 1";
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

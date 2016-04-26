package acm1pt.badassapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstatusFragment extends BaseVolleyFragment {

    private TextView label;
    private Button connector;

    public static EstatusFragment newInstance() {
        EstatusFragment fragment = new EstatusFragment();
        return fragment;
    }

    public EstatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_estatus, container, false);
        label = (TextView) v.findViewById(R.id.label);
        connector = (Button) v.findViewById(R.id.connection_button);
        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest(){
        String url = "http://badasshouse.ddns.net:81/WebServices/WS_Login?wsdl";
        StringRequest request = new StringRequest(url,

                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        label.setText(response);
                        onConnectionFinished();
                    }
                }

                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        onConnectionFailed(volleyError.toString());
                    }
                }
        );

        addToQueue(request);
    }

}

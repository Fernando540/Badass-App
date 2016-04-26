package acm1pt.badassapp;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class login extends AppCompatActivity implements View.OnClickListener{
    //Constantes para la invocacion del web service
    private static final String NAMESPACE = "http://192.168.1.72:8181/";
    private static String URL="http://192.168.1.72:8181/WebServices/WS_Login?wsdl";
    private static final String METHOD_NAME = "WS_Login";
    private static final String SOAP_ACTION ="http://192.168.1.72:8181/WS_Login";

    //Declaracion de variables para consuymir el web service
    private SoapObject request=null;
    private SoapSerializationEnvelope envelope=null;
    private SoapPrimitive  resultsRequestSOAP=null;

    //Declaracion de variables para serealziar y deserealizar objetos y cadenas JSON
    Gson gson ;

    //Variables para manipular los controles de la UI
    EditText correin;
    EditText pass;
    ProgressBar pg;
    String correo, password, resultLogin;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correin=(EditText)findViewById(R.id.correo);
        pass=(EditText)findViewById(R.id.password);
        entrar=(Button) findViewById(R.id.btn);
        pg = (ProgressBar) findViewById(R.id.progressBar1);
        entrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (correin.getText().length() != 0 && correin.getText().toString() != "") {
            correo = correin.getText().toString();
            if (pass.getText().length() != 0 && pass.getText().toString() != "") {
                password = pass.getText().toString();
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Ingresa tu contraseña!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Ingresa tu correo!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void LoginSucces(String resultado){
        if(resultado.equals("invalido")){
            Context context = getApplicationContext();
            CharSequence text = "Usuario Invalido!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else {
            Context context = getApplicationContext();
            CharSequence text = "Bienvenido "+resultado;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
            finish();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            resultLogin = WebService.invokeLogin(correo,password, "login");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pg.setVisibility(View.INVISIBLE);
            LoginSucces(resultLogin);
        }

        @Override
        protected void onPreExecute() {
            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}
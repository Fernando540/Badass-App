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

import java.sql.ResultSet;
import java.sql.SQLException;

<<<<<<< HEAD
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
=======

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText correin;
    EditText pass;
    Toast toast1;
    private Button entrar;
    private final String clave="777888222333";
    acm1pt.badassapp.cDatos datos=new cDatos();
    acm1pt.badassapp.cifraCesar cesar=new cifraCesar();
    acm1pt.badassapp.cifraSha sha=new cifraSha();
    ResultSet rs;
    String resultado="",contra,contra1;
>>>>>>> origin/master

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
<<<<<<< HEAD
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
=======
        if(v==entrar){
            /*String NAMESPACE = "http://WSBadassHouse/";
            String URL = "http://192.168.1.72:8181/WebServices/WS_Login?wsdl";
            String METHOD_NAME = "WS_Login";
            String SOAP_ACTION = "http://192.168.1.72:8181/WS_Login";
            request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("correo",correin.getText().toString() );
            request.addProperty("pass",pass.getText().toString());
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
                String res = resultado_xml.toString();


                    Toast toast1 = Toast.makeText(getApplicationContext(),res.toString(), Toast.LENGTH_SHORT);
                    toast1.show();

            }
            catch (Exception e)
            {
                Toast toast1 = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT);
                toast1.show();
>>>>>>> origin/master
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

<<<<<<< HEAD
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
=======
            //Almacenamos el resultado en un String ya que lo que represa
            //el ws es una cadena json, representando una lista AndroidOS
            //de objetos del tipo
            //String  strJSON = resultsRequestSOAP.toString();
            contra=cesar.Cifrado(pass.getText().toString());
            contra1=sha.cifrar(contra);

            try {
                datos.conectar();
                //datos.setAccion(correin.getText().toString(),contra1 , clave);
                rs = datos.consulta1("call valida('" + correin + "',AES_ENCRYPT('" + contra1 + "','" + clave + "'));");
                while (rs.next()) {
                    if (rs.getString("Estatus").equals("1")) {
                        if(rs.getString("nName")!=null){
                            resultado=rs.getString("nName");
                            toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Bienvenido: "+resultado, Toast.LENGTH_SHORT);
                            toast1.show();
                            /*Intent intent = new Intent(this,home.class);
                            intent.putExtra("tipoUsr","Junior");
                            startActivity(intent);
                            finish();*/
                        }else{
                            toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "Usuario Invalido", Toast.LENGTH_SHORT);

                            toast1.show();
                        }

                    } else {
                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Usuario Invalido", Toast.LENGTH_SHORT);

                        toast1.show();
                    }
                }
            }catch(SQLException e){
                toast1 =
                        Toast.makeText(getApplicationContext(),
                                e.toString(), Toast.LENGTH_SHORT);

                toast1.show();
            }

            /*if(correin.getText().toString().equals("fer@gmail.com")){
                Intent intent = new Intent(this,home.class);
                intent.putExtra("tipoUsr","Junior");
                startActivity(intent);
                finish();
            }else{
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Usuario Invalido", Toast.LENGTH_SHORT);
>>>>>>> origin/master

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}
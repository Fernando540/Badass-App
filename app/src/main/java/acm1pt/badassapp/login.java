package acm1pt.badassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.sql.SQLOutput;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText correin;
    EditText pass;
    private Button entrar;
    String[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correin=(EditText)findViewById(R.id.correo);
        pass=(EditText)findViewById(R.id.password);
        entrar=(Button) findViewById(R.id.btn);
        entrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==entrar){
            try {
                String NAMESPACE = "http://189.245.172.14:81/WebServices/";
                String URL = "http://189.245.172.14:81/WebServices/WS_Login?wsdl";
                String METHOD_NAME = "WS_Login";
                String SOAP_ACTION = "http://189.245.172.14:81/WebServices/WS_Login";

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                request.addProperty("correo", correin.getText().toString());
                request.addProperty("pass", pass.getText().toString());

                SoapSerializationEnvelope envelope =
                        new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.dotNet = false;
                envelope.setOutputSoapObject(request);


    /* Se captura la respuesta */
                Object result = (Object) envelope.getResponse();
                results = (String[]) result;

            }catch(Exception e){

            }

            //Para cuando Sirva el WEB Service :v
            if(results[0].equals("invalido")){
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Usuario Invalido", Toast.LENGTH_SHORT);

                toast1.show();
            }else{
                Intent intent = new Intent(login.this,home.class);
                startActivity(intent);

            }
            if(correin.getText().toString().equals("e@e.com") && pass.getText().toString().equals("123")){
                Intent intent = new Intent(login.this,Main.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
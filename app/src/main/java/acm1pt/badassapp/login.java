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
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLOutput;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText correin;
    EditText pass;
    private Button entrar;
    String[] results;
    private SoapObject request=null;
    private SoapSerializationEnvelope envelope=null;
    private SoapPrimitive resultsRequestSOAP=null;


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
            String NAMESPACE = "http://badasshouse.ddns.net:81/WebServices/";
            String URL = "http://badasshouse.ddns.net:81/WebServices/WS_Login?wsdl";
            String METHOD_NAME = "WS_Login";
            String SOAP_ACTION = "http://badasshouse.ddns.net:81/WebServices/WS_Login";
            request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("correo",correin.getText().toString() );
            request.addProperty("pass",pass.getText().toString());
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = false;


            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);

            try {

                transporte.call(SOAP_ACTION, envelope);


                resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();

            } catch (IOException e) {
                String mensage= e.toString();
                // TODO Auto-generated catch block
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                mensage, Toast.LENGTH_SHORT);

                toast1.show();

            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                e.toString(), Toast.LENGTH_SHORT);

                toast1.show();
            }

            //Almacenamos el resultado en un String ya que lo que represa
            //el ws es una cadena json, representando una lista AndroidOS
            //de objetos del tipo
            //String  strJSON = resultsRequestSOAP.toString();
            if(correin.getText().toString().equals("a")){
                Intent intent = new Intent(this,home.class);
                intent.putExtra("tipoUsr","Junior");
                startActivity(intent);
                finish();
            }else{
                /*Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Usuario Invalido", Toast.LENGTH_SHORT);

                toast1.show();*/
            }
        }
    }
}
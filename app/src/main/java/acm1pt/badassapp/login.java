package acm1pt.badassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.sql.SQLOutput;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText correin;
    EditText pass;
    private Button entrar;

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
            String NAMESPACE = "badasshouse.prograbatiz.com/WebServices/";
            String URL = "badasshouse.prograbatiz.com/WebServices/WS_Login?wsdl";
            String METHOD_NAME = "WS_Login";

    /* Definir los parámetros del servicio web, que son los datos
       que se extrajo de la descripción del servicio.
    */
            String SOAP_ACTION = "badasshouse.prograbatiz.com/WebServices/WS_Login";

    /* Se crea el paquete SOAP a enviar con la petición al servicio web,
       en este caso es el valor que se quiere convertir a grados Fahrenheit en este
       ejemplo, de esto se encarga el método addProperty("<Propiedad>",<Valor>).
       NOTA: A la propiedad envelope.dotNet se le da un valor de true debido a que
       el servicio Web al cual nos queremos conectar esta codificado en .NET,
       no siempre es así, esta información debería estar en el sitio oficial
       que atiende este servicio web.
    */

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            request.addProperty(correin.getText().toString(), pass.getText().toString());
            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);


    /* Se captura la respuesta y se muestra en el TextView */
            String respuesta;
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject result=(SoapObject) envelope.getResponse();
                respuesta = result.getProperty("resultado").toString();

            }
            catch(Exception e){
                e.printStackTrace();
            }

            //Para cuando Sirva el WEB Service :v
            /*if(respuesta.equals("invalido")){

            }else{
                Intent intent = new Intent(login.this,home.class);
                startActivity(intent);
                finish();
            }*/
            if(correin.getText().toString().equals("e@e.com") && pass.getText().toString().equals("123")){
                Intent intent = new Intent(login.this,home.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
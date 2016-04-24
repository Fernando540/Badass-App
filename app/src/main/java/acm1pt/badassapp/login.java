package acm1pt.badassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;


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
            }

            if(correin.getText().toString().equals("fer@gmail.com")){
                Intent intent = new Intent(this,home.class);
                intent.putExtra("tipoUsr","Junior");
                startActivity(intent);
                finish();
            }


            /*try {

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
            }*/

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

                toast1.show();
            }*/
        }
    }
}
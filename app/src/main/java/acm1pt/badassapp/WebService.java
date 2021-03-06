package acm1pt.badassapp;

/**
 * Created by ferna on 25/04/2016.
 */
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebService {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://WSBadassHouse/";
    //Webservice URL - WSDL File location
    private static String URLcesar = "http://192.168.1.72:8181/WebServices/CifraCesar?wsdl";
    private static String URLsha = "http://192.168.1.72:8181/WebServices/CifraSha?wsdl";
    private static String URLlog = "http://192.168.1.72:8181/WebServices/WS_Login?wsdl";
    private static String URLGenerico="http://192.168.1.72:8181/WebServices/WSGenerico?wsdl";


    /*
    private static String URLcesar = "http://192.168.1.72:8181/WebServices/CifraCesar?wsdl";
    private static String URLsha = "http://192.168.1.72:8181/WebServices/CifraSha?wsdl";
    private static String URLlog = "http://192.168.1.72:8181/WebServices/WS_Login?wsdl";
    private static String URLTipo="http://badasshouse.ddns.net:81/WebServices/WSGenerico?wsdl";
    private static String URLGenerico="http://badasshouse.ddns.net:81/WebServices/WSGenerico?wsdl";*/

    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://WSBadassHouse/";

    public static String invokeLogin(String mail, String pass, String webMethName) {

        String resTxt = null;
        String passCesar = CifraCesar(pass);
        String passSha = CifraSha(passCesar);


        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        request.addProperty("correo", mail);
        request.addProperty("pass", passSha);

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLlog);

        try
        {
            transporte.call(SOAP_ACTION+webMethName, envelope);
            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
            resTxt = response.toString();
        }
        catch (Exception e)
        {
            resTxt = "invalido";
        }

        //Return resTxt to calling object
        return resTxt;
    }

    public static String CifraCesar(String plaintext){
        String txtCesar = null;
        SoapObject request = new SoapObject(NAMESPACE, "cifraC");
        request.addProperty("plaintext1", plaintext);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLcesar);

        try
        {
            transporte.call(SOAP_ACTION+"cifraC", envelope);
            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
            txtCesar = response.toString();
        }
        catch (Exception e)
        {
            txtCesar = "Error occured";
        }

        return txtCesar;
    }

    public static String CifraSha(String txtCesar){
        String txtSha = null;
        SoapObject request = new SoapObject(NAMESPACE, "cifraS");
        request.addProperty("plaintext2", txtCesar);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLsha);

        try{
            transporte.call(SOAP_ACTION+"cifraS", envelope);
            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
            txtSha = response.toString();
        }
        catch (Exception e) {
            txtSha = "Error occured";
        }
        return txtSha;
    }

    public static String dimeTipo(String correo){
        String tipo="";
        SoapObject request = new SoapObject(NAMESPACE, "dimeTipo");
        request.addProperty("correo", correo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLGenerico);

        try
        {
            transporte.call(SOAP_ACTION+"dimeTipo", envelope);
            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
            tipo = response.toString();
        }
        catch (Exception e)
        {
            tipo = "Error occured";
        }
        return tipo;
    }

    public static void onOff(String correo, String voltaje,String contact, String habit){
        SoapObject request = new SoapObject(NAMESPACE, "simulaCorr");
        request.addProperty("correo", correo);
        request.addProperty("volt", voltaje);
        request.addProperty("habit", habit);
        request.addProperty("contact", contact);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLGenerico);

        try
        {
            transporte.call(SOAP_ACTION+"simulaCorr", envelope);
        }
        catch (Exception e)
        {

        }
    }

    public static String[] enchufes (String mail, String hName){
        String [] usos = new String[4];
        SoapObject request = new SoapObject(NAMESPACE, "enchuState");
        request.addProperty("correo", mail);
        request.addProperty("habName", hName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URLsha);

        try{
            transporte.call(SOAP_ACTION+"enchuState", envelope);
            SoapObject obj1 = (SoapObject) envelope.bodyIn;

            SoapObject obj2 =(SoapObject) obj1.getProperty(0);
            for (int i = 0; i< obj2.getPropertyCount(); i++)
            {
                // int id1 = Integer.parseInt(obj2.getProperty(0).toString());
                String id1 = obj2.getProperty(0).toString();
                usos[i] = id1;
            }
        }
        catch (Exception e) {

        }

        return usos;
    }
}

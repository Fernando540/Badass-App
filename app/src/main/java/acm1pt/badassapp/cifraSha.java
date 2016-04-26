package acm1pt.badassapp;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cifraSha {

    public String cifrar(String textoOriginal) {
        String textoCifrado = "";


        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }

        try {
            md.update(textoOriginal.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }

        byte raw[] = md.digest();
        //String s = (new BASE64Encoder()).encode(raw);
        ByteArrayInputStream input = new ByteArrayInputStream(raw);
        String cadAux;
        String resultado = "";
        int leido = input.read();
        while (leido != -1) {
            cadAux = Integer.toHexString(leido);
            if (cadAux.length() < 2) {
                resultado += "0";
            }
            resultado += cadAux;
            leido = input.read();
        }
        textoCifrado=resultado;
        return textoCifrado;
    }
}

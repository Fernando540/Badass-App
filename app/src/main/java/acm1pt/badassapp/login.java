package acm1pt.badassapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener{
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
            //BACKDOOR
            if(correo.equals("fer@gmail.com")){
                Intent intent = new Intent(this, home.class);
                startActivity(intent);
                finish();
            }
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
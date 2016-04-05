package acm1pt.badassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLOutput;

public class login1 extends AppCompatActivity implements View.OnClickListener{
    EditText correin;
    EditText pass;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        correin=(EditText)findViewById(R.id.correo);
        pass=(EditText)findViewById(R.id.password);
        entrar=(Button) findViewById(R.id.btn);
        entrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==entrar){
            if(correin.getText().toString().equals("e@e.com") && pass.getText().toString().equals("123")){
                Intent intent = new Intent(login1.this,home.class);
                startActivity(intent);
                finish();
            }
        }
    }
}

package com.evidencia2.ea2tallerpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText txtusu, txtclave;
    Button btnregistra,btningresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
        public void menu(View view){

            if (txtusu != null && txtclave != null){

                Toast err = Toast.makeText(getApplicationContext(),"Usuario o clave Invalidos, intenta de nuevo",Toast.LENGTH_LONG);
                err.show();


            }else {

                Toast log = Toast.makeText(getApplicationContext(),"Bienvenido al sistema",Toast.LENGTH_LONG);
                log.show();
                Intent Menu = new Intent(getApplicationContext(), menu.class);
                startActivity(Menu);

            }






    }

    public void Registro(View view) {
        Intent registrar = new Intent(getApplicationContext(),Registro.class);
        startActivity(registrar);
    }
}
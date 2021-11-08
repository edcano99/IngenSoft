package com.evidencia2.ea2tallerpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    EditText txtusu,txtpass;
    Button btnregistro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtusu= findViewById(R.id.txtususario);
        txtpass = findViewById(R.id.txtclave);

        btnregistro = findViewById(R.id.btnregistrar);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usu = txtusu.getText().toString();
                String passw= txtpass.getText().toString();
                finish();

                    nuevoVh R1 = new nuevoVh(usu,passw);

                    txtusu.setText("");
                    txtpass.setText("");

            }
        });

    }

    public void Registro(View view) {
    }
}
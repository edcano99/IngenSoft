package com.evidencia2.ea2tallerpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class menu extends AppCompatActivity  {

    EditText txtIdVehiculo,txtPlaca,txtMarca,txtModelo,txtAnio,txtColor;
    Button btnnuevo,btnmodificar,btnlistar,btneliminar;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtIdVehiculo = findViewById(R.id.txtIdVehiculo);
        txtPlaca = findViewById(R.id.txtplaca);
        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtAnio = findViewById(R.id.txtAnio);
        txtColor= findViewById(R.id.txtColor);

        btnnuevo = findViewById(R.id.btnNuevo);
        btnmodificar = findViewById(R.id.btnmodificar);
        btnlistar=findViewById(R.id.bbtnlistar);
        btneliminar = findViewById(R.id.btneliminar);

        btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= Integer.parseInt(txtIdVehiculo.getText().toString());
                String placa= txtPlaca.getText().toString();
                String marca = txtMarca.getText().toString();
                String modelo=txtModelo.getText().toString();
                int anio= Integer.parseInt(txtAnio.getText().toString());
                String color = txtColor.getText().toString();


                    nuevoVh V1= new nuevoVh(id,placa,marca,modelo,anio,color);
                    V1.save();
                    txtIdVehiculo.setText("");
                    txtPlaca.setText("");
                    txtMarca.setText("");
                    txtModelo.setText("");
                    txtAnio.setText("");
                    txtColor.setText("");

                }




        });
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtIdVehiculo.getText().toString());
                nuevoVh V1 = nuevoVh.findById(nuevoVh.class, (long) id);
                String placa= txtPlaca.getText().toString();
                String marca = txtMarca.getText().toString();
                String modelo=txtModelo.getText().toString();
                int anio= Integer.parseInt(txtAnio.getText().toString());
                String color = txtColor.getText().toString();

                V1.setPlaca(placa);
                V1.setMarca(marca);
                V1.setModelo(modelo);
                V1.setAnio(anio);
                V1.setColor(color);
                V1.save();

                nuevoVh mod = new nuevoVh(id,placa,marca,modelo,anio,color);
                mod.save();
                txtIdVehiculo.setText("");
                txtPlaca.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtAnio.setText("");
                txtColor.setText("");

            }
        });

        btnlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int id= Integer.parseInt(txtIdVehiculo.getText().toString());
                nuevoVh V1 = nuevoVh.findById(nuevoVh.class, (long) id);
                txtPlaca.setText(V1.getPlaca());
                txtMarca.setText(V1.getMarca());
                txtModelo.setText(V1.getModelo());
                txtAnio.setText(String.valueOf( V1.getAnio()));
                txtColor.setText(V1.getColor());
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id=Integer.parseInt(txtIdVehiculo.getText().toString());
                nuevoVh borr = nuevoVh.findById(nuevoVh.class,(long) id);
                borr.delete();

                txtIdVehiculo.setText("");
                txtPlaca.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtAnio.setText("");
                txtColor.setText("");



            }
        });

    }


    public void salid(View view){

        finish();
    }
}
package com.ods.autoscolombia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnVehiculo,btnContratar, btnPagos, btnPropietario, btnIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnVehiculo=findViewById(R.id.btn_menu_vehic);
        btnContratar=findViewById(R.id.btn_menu_contratar);
        btnPagos=findViewById(R.id.btn_menu_pagos);
        btnPropietario=findViewById(R.id.btn_menu_propietario);
        btnIngreso=findViewById(R.id.btn_menu_ingreso);

    }

    public void irVehiculo(View view){
        Intent intentIrVehiculo = new Intent(this,Vehiculos.class);
        startActivity(intentIrVehiculo);
    }

    public void irPropietario(View view){
        Intent intentIrPropietario = new Intent(this,Propietario.class);
        startActivity(intentIrPropietario);
    }

    public void irContratar(View view){
        Intent intentIrContratar = new Intent(this,ContratarCelda.class);
        startActivity(intentIrContratar);
    }

    public void irIngreso(View view){
        Intent intentIrIngreso = new Intent(this,Ingresos.class);
        startActivity(intentIrIngreso);
    }

    public void irPagos(View view){
        Intent intentIrPagos = new Intent(this,Pagos.class);
        startActivity(intentIrPagos);
    }

}
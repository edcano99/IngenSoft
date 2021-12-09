package com.ods.autoscolombia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ods.autoscolombia.db.DbVehiculo;

public class Pagos extends AppCompatActivity {

    TableLayout tbpagos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);

        tbpagos = findViewById(R.id.tb_pagos);

    }

    public void irAtrasVehiculo(View view){
        Intent intentAtrasVehiculo = new Intent(this, Menu.class);
        startActivity(intentAtrasVehiculo);

    }

    public void llenarTabla(View view) {

        DbVehiculo adm = new DbVehiculo(this, "parqueadero", null, 1);
        SQLiteDatabase db = adm.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from pagos", null);


        if (!fila.moveToFirst()) {

            Toast.makeText(this, "No hay pagos", Toast.LENGTH_LONG).show();

        }else {

            do {
                View registro = LayoutInflater.from(this).inflate(R.layout.item_pagos_tabla, null, false);
//                TextView tbid = (TextView) registro.findViewById(R.id.tbid);
                TextView tbplaca = (TextView) registro.findViewById(R.id.tbplaca);
                TextView tbpago = (TextView) registro.findViewById(R.id.tbpago);
                TextView tbmensualidad = (TextView) registro.findViewById(R.id.tbmensualidad);

//                tbid.setText(fila.getString(0));
                tbplaca.setText(fila.getString(1));
                tbpago.setText(fila.getString(2));
                tbmensualidad.setText(fila.getString(3));

                tbpagos.addView(registro, 0);

            } while (fila.moveToNext());
        }
        fila.close();

    }

}
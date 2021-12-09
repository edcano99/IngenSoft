package com.ods.autoscolombia.entidades;

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

import com.ods.autoscolombia.Menu;
import com.ods.autoscolombia.R;
import com.ods.autoscolombia.db.DbVehiculo;

public class Celdas extends AppCompatActivity {

    TableLayout tbceldas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celdas);

        tbceldas = findViewById(R.id.tb_celdas);
    }

    public void irAtrasVehiculo(View view){
        Intent intentAtrasVehiculo = new Intent(this, Menu.class);
        startActivity(intentAtrasVehiculo);

    }

    public void llenarTabla(View view) {

        DbVehiculo adm = new DbVehiculo(this, "parqueadero", null, 1);
        SQLiteDatabase db = adm.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from celdas", null);


        if (!fila.moveToFirst()) {

            Toast.makeText(this, "No hay celdas, procede a crearlas", Toast.LENGTH_LONG).show();
            // crea celdas por defecto
            for (int i = 1; i <= 5; i++) {
                db.execSQL("Insert into celdas ( id, detalle) values ("+i+",'parqueadero " + i + "')");
            }
        }

        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.item_celdas_tabla, null, false);
//            TextView tbid = (TextView) registro.findViewById(R.id.tbid);
            TextView tbdetalle = (TextView) registro.findViewById(R.id.tbdetalle);
            TextView tbdisponible = (TextView) registro.findViewById(R.id.tbdisponible);
            TextView tbplaca = (TextView) registro.findViewById(R.id.tbplaca);


//            tbid.setText(fila.getString(0));
            tbdetalle.setText(fila.getString(1));
            tbdisponible.setText(fila.getString(2));
            tbplaca.setText(fila.getString(3));

            tbceldas.addView(registro,0);

        } while (fila.moveToNext());

        fila.close();

    }


}
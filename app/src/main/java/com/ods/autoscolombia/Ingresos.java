package com.ods.autoscolombia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ods.autoscolombia.db.DbVehiculo;
import com.ods.autoscolombia.entidades.MiVehiculo;

public class Ingresos extends AppCompatActivity {

    EditText placa, idpropietario, nombre, telefono, observaciones, mensualidad, idcelda;
TextView celda;
    MiVehiculo miVehiculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        placa = findViewById(R.id.txt_ingresos_placa);
        idpropietario = findViewById(R.id.txt_ingresos_idpropietario);
        nombre = findViewById(R.id.txt_ingresos_nombre);
        telefono = findViewById(R.id.txt_ingresos_tele);
        celda = findViewById(R.id.txt_ingresos_celda);
        observaciones = findViewById(R.id.txt_ingresos_observaciones);
        mensualidad = findViewById(R.id.txt_ingresos_pago);

    }

    public void irAtrasVehiculo(View view) {
        Intent intentAtrasVehiculo = new Intent(this, Menu.class);
        startActivity(intentAtrasVehiculo);

    }

    public void irPagos(View view){
        Intent intentPagos = new Intent(this,Pagos.class);
        startActivity(intentPagos);

    }

    private void limpiar() {
        placa.setText("");
        idpropietario.setText("");
        celda.setText("");
        nombre.setText("");
        telefono.setText("");
        observaciones.setText("");
        mensualidad.setText("");

    }

    public void ConsultarVehiculo(View view) {

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();

        Cursor fila = db.rawQuery("select v.idpropietario, c.id, p.Cedula, p.Nombre, p.Telefono, i.observaciones, i.mensualidad " +
                "from vehiculo as v join " +
                "propietarios as p on p.Cedula = v.idpropietario left join " +
                "celdas as c on c.placa = v.placa  LEFT join " +
                "ingresos as i on i.idcelda = c.id  " +
                "where v.placa='" + pla + "'", null);

        if (fila.moveToFirst()) {

            idpropietario.setText(fila.getString(0));
            if (fila.getString(1) != null) {
                celda.setText(fila.getString(1));
            }

            nombre.setText(fila.getString(3));
            telefono.setText(fila.getString(4));
            observaciones.setText(fila.getString(5));
            mensualidad.setText(fila.getString(6));


        } else {
            Toast.makeText(this, "No se encontro ningun registro asociada a esa placa.", Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    public void GuardarCelda(View view) {

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();

        //consulto tabla celdas para obtener la primera disponible

        Cursor fila = db.rawQuery("select *  " +
                "from celdas " +
                "where placa='" + pla + "'", null);


        if (fila.moveToFirst()) {
            // existe registro celda por tanto SOLO SE ACTUALIZA LA TABLA INGREESOS
            ContentValues datos = new ContentValues();

            datos.put("mensualidad",mensualidad.getText().toString());
            datos.put("observaciones", observaciones.getText().toString());

            int val = db.update("ingresos", datos, "id="+fila.getString(0),null);

            Toast.makeText(Ingresos.this, "El vehiculo ya esta asignado a una celda, solo se actualizan datos.", Toast.LENGTH_LONG).show();

        }else {
            // no existe registro celda por tanto se asigna

            Cursor registrocelda = db.rawQuery("select *  " +
                    "from celdas " +
                    "where disponible = 1 LIMIT 1", null);

            if (registrocelda.moveToFirst()) {

                // ACTUALIZAMOS LA CELDA , se le asigna la placa y se marca QUE YA NO ESTA DISPONIBLE
                ContentValues datos = new ContentValues();

                datos.put("placa",pla);
                datos.put("disponible", false);

                int val = db.update("celdas", datos, "id="+registrocelda.getString(0),null);

                // se crea registro de ingreso

                ContentValues datosingresos = new ContentValues();

                datosingresos.put("observaciones",observaciones.getText().toString());
                datosingresos.put("mensualidad",mensualidad.getText().toString());
                datosingresos.put("idcelda",registrocelda.getString(0));

                db.insert("ingresos", null,datosingresos);

                // se crea registro de pagos

                ContentValues datospagos = new ContentValues();

                datospagos.put("placa",pla);
                datospagos.put("pago",1);
                datospagos.put("mensualidad",mensualidad.getText().toString());

                db.insert("pagos", null,datospagos);


                Toast.makeText(Ingresos.this, "El vehiculo HA SIDO asignado a una celda", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(Ingresos.this, "No hay celdas disponibles para asignar", Toast.LENGTH_LONG).show();
            }

db.close();
            limpiar();
        }




    }


    public void salidaIngreso(View view){

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String idcelda = celda.getText().toString();

        if(celda!=null){

            ContentValues datos = new ContentValues();

            datos.put("disponible", true);
            datos.put("placa","");

            db.update("celdas", datos, "id="+idcelda,null);

            limpiar();

            Toast.makeText(this,"Se ha generado la salida de manera exitosa",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"No se puede generar salida sin un ingreso",Toast.LENGTH_LONG).show();
        }


        db.close();



    }

}
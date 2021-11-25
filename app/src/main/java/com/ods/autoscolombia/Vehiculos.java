package com.ods.autoscolombia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ods.autoscolombia.db.DbVehiculo;
import com.ods.autoscolombia.entidades.MiVehiculo;


public class Vehiculos extends AppCompatActivity {

    EditText placa, modelo, ano, marca, color, matricula, propietario;

    MiVehiculo miVehiculo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);

        placa=findViewById(R.id.txt_vehiculos_placa);
        modelo=findViewById(R.id.txt_vehiculos_modelo);
        ano=findViewById(R.id.txt_vehiculos_ano);
        marca=findViewById(R.id.txt_vehiculos_marca);
        color=findViewById(R.id.txt_vehiculos_color);
        matricula=findViewById(R.id.txt_vehiculos_matricula);
        propietario=findViewById(R.id.txt_vehiculos_propietario);

    }

    public void irAtrasVehiculo(View view){
        Intent intentAtrasVehiculo = new Intent(this,Menu.class);
        startActivity(intentAtrasVehiculo);

    }

    public void metodoCrear(View view){

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();
        String mar = marca.getText().toString();
        String mod = modelo.getText().toString();
        String col = color.getText().toString();
        int an = Integer.parseInt(ano.getText().toString());
        String matri = matricula.getText().toString();
        int prop = Integer.parseInt(propietario.getText().toString());

        ContentValues datos = new ContentValues();

        datos.put("placa", pla);
        datos.put("marca", mar);
        datos.put("modelo", mod);
        datos.put("color", col);
        datos.put("ano", an);
        datos.put("matricula", matri);
        datos.put("idpropietario", prop);

        db.insert("vehiculo",null,datos);
        db.close();


        if(placa.getText().equals("") ){
            Toast.makeText(Vehiculos.this, "Error al guardar vehiculo", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(Vehiculos.this, "Registro de Vehiculo guardado", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void limpiar(){
        placa.setText("");
        marca.setText("");
        modelo.setText("");
        color.setText("");
        ano.setText("");
        matricula.setText("");
        propietario.setText("");

    }


    public void metodoListarVehiculo(View view) {

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();


        Cursor fila = db.rawQuery("select * from vehiculo where placa='"+pla+"'",null);

        if (fila.moveToFirst()){

            marca.setText(fila.getString(1));
            modelo.setText(fila.getString(2));
            color.setText(fila.getString(3));
            ano.setText(fila.getString(4));
            matricula.setText(fila.getString(5));
            propietario.setText(fila.getString(6));

        }else{
            Toast.makeText(this,"El vehículo no se encontro", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void Eliminar(View view){

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();

        int val = db.delete("vehiculo","placa='"+pla+"'",null);

        db.close();



        if (val==1){
            Toast.makeText(this,"Vehículo fue eliminado",Toast.LENGTH_LONG).show();
            limpiar();
        }else{
            Toast.makeText(this,"error Vehículo no existe",Toast.LENGTH_LONG).show();
        }

    }


    public void Editar(View view){

        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();

        String pla = placa.getText().toString();
        String mar = marca.getText().toString();
        String mod = modelo.getText().toString();
        String col = color.getText().toString();
        int an = Integer.parseInt(ano.getText().toString());
        String matri = matricula.getText().toString();
        int prop = Integer.parseInt(propietario.getText().toString());

        ContentValues datos = new ContentValues();

        datos.put("placa", pla);
        datos.put("marca", mar);
        datos.put("modelo", mod);
        datos.put("color", col);
        datos.put("ano", an);
        datos.put("matricula", matri);
        datos.put("idpropietario", prop);

        int val = db.update("vehiculo", datos, "placa='"+pla+"'",null);
        db.close();

        limpiar();

        if (val==1){
            Toast.makeText(this,"Vehículo actualizado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error Vehículo actualizado",Toast.LENGTH_LONG).show();
        }


    }

}
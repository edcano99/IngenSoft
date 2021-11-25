package com.ods.autoscolombia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ods.autoscolombia.db.DbVehiculo;

public class Propietario extends AppCompatActivity {

    EditText Nombre,Cedula,Direccion,Telefono,Correo,Usuario,Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propietario);

        Nombre = findViewById(R.id.txt_prop_nombre);
        Cedula = findViewById(R.id.txt_prop_cedula);
        Direccion = findViewById(R.id.txt_prop_direccion);
        Telefono = findViewById(R.id.txt_prop_tel);
        Correo = findViewById(R.id.txt_prop_correo);
        //createDBPropietario();
    }

    private void createDBPropietario(){
        DbVehiculo dbVehiculos = new DbVehiculo(this, "parqueadero", null, 1);

        SQLiteDatabase db = dbVehiculos.getWritableDatabase();
        dbVehiculos.onCreate(db);
    }

    public void  Crear (View view){
        DbVehiculo adm = new DbVehiculo(this, "parqueadero", null,1);
        SQLiteDatabase db = adm.getWritableDatabase();

        int Ced = Integer.parseInt(Cedula.getText().toString());
        String nom = Nombre.getText().toString();
        String dir = Direccion.getText().toString();
        String tel = Telefono.getText().toString();
        String cor = Correo.getText().toString();


        ContentValues datos = new ContentValues();

        datos.put("Cedula",Ced);
        datos.put("Nombre",nom);
        datos.put("Direccion",dir);
        datos.put("Telefono",tel);
        datos.put("Correo",cor);

        db.insert("propietarios", null,datos);
        db.close();

        Cedula.setText("");
        Nombre.setText("");
        Direccion.setText("");
        Telefono.setText("");
        Correo.setText("");


        if(Cedula.getText().equals("") ){
            Toast.makeText(Propietario.this, "Debe ingresar el número de cedula", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Propietario.this, "Registro guardado con exito", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void limpiar(){
        Cedula.setText("");
        Nombre.setText("");
        Direccion.setText("");
        Telefono.setText("");
        Correo.setText("");


    }

    public void Listar (View view){

        DbVehiculo adm = new  DbVehiculo(this,"parqueadero", null,1);
        SQLiteDatabase db = adm.getWritableDatabase();

        int cedula = Integer.parseInt(Cedula.getText().toString());

        Cursor fil = db.rawQuery("select * from propietarios where Cedula ="+cedula,null);


        if(fil.moveToFirst()){
            Nombre.setText(fil.getString(1));
            Direccion.setText(fil.getString(2));
            Telefono.setText(fil.getString(3));
            Correo.setText(fil.getString(4));


        }else{
            Toast.makeText(this,"El propietario no existe",Toast.LENGTH_LONG).show();

        }
        db.close();
    }

    public void Editarpropietario(View view){
        DbVehiculo adm = new DbVehiculo(this, "parqueadero", null,1);
        SQLiteDatabase db = adm.getWritableDatabase();

        int Ced = Integer.parseInt(Cedula.getText().toString());
        String nom = Nombre.getText().toString();
        String dir = Direccion.getText().toString();
        String tel = Telefono.getText().toString();
        String cor = Correo.getText().toString();


        ContentValues datos = new ContentValues();

        datos.put("Cedula",Ced);
        datos.put("Nombre",nom);
        datos.put("Direccion",dir);
        datos.put("Telefono",tel);
        datos.put("Correo",cor);

        int val = db.update("propietarios", datos, "Cedula="+Ced,null);
        db.close();

        limpiar();

        if (val==1){
            Toast.makeText(this,"Propietario actualizado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error Propietario",Toast.LENGTH_LONG).show();
        }

    }

    public void Eliminarpropietario(View view){

        DbVehiculo adm = new DbVehiculo(this, "parqueadero", null,1);
        SQLiteDatabase db = adm.getWritableDatabase();

        int Ced = Integer.parseInt(Cedula.getText().toString());

        int val = db.delete("propietarios","Cedula="+Ced,null);

        db.close();



        if (val==1){
            Toast.makeText(this,"Propietario fue eliminado",Toast.LENGTH_LONG).show();
            limpiar();
        }else{
            Toast.makeText(this,"error Propietario no existe",Toast.LENGTH_LONG).show();
        }

    }

}
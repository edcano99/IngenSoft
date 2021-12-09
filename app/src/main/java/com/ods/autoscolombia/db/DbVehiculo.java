package com.ods.autoscolombia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbVehiculo extends SQLiteOpenHelper {


    public DbVehiculo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table vehiculo(placa text primary key, marca text, modelo text, color text, ano integer, matricula text, idpropietario integer)");
        db.execSQL("create table propietarios (Cedula integer primary key,Nombre text,Direccion text, Telefono text,Correo text)");
        db.execSQL("create table ingresos(id integer primary key, observaciones text, mensualidad integer, idcelda integer)");
        db.execSQL("create table celdas(id integer primary key, detalle text, disponible boolean default 1, placa text )");
        db.execSQL("create table pagos(id integer primary key autoincrement, placa text, pago boolean default 0, mensualidad integer)");


//db.close();
    }
   /* public void CreatePropietarioDB(SQLiteDatabase db)
    {
        try{
        {
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists vehiculo");
        db.execSQL("create table vehiculo(placa text primary key, marca text, modelo text, color text, ano integer, matricula text, idpropietario integer)");
        db.execSQL("drop table if exists propietarios");
        db.execSQL("create table propietarios (Cedula integer primary key,Nombre text,Direccion text, Telefono text,Correo text)");
        db.execSQL("drop table if exists ingresos");
        db.execSQL("create table ingresos(id integer primary key, observaciones text, mensualidad integer, idcelda integer)");
        db.execSQL("drop table if exists celdas");
        db.execSQL("create table celdas(id integer primary key, detalle text, disponible boolean default 1, placa text )");
        db.execSQL("drop table if exists pagos");
        db.execSQL("create table pagos(id integer primary key, placa text, pago boolean default 0)");

    }
}

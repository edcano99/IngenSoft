package com.evidencia2.ea2tallerpractico;


import android.widget.EditText;

import com.orm.SugarRecord;

public class nuevoVh extends SugarRecord<nuevoVh> {

    private int id_vehiculo;
    private String placa;
    private String marca;
    private String Modelo;
    private int anio;
    private String color;
    private  String Usuario;
    private  String clave;

    public nuevoVh(String usu, String passw){

    }
    public  void registrar(){

    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {

        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.Modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public nuevoVh(int id_vehiculo, String placa, String marca, String modelo, int anio, String color){
        this.id_vehiculo = id_vehiculo;
        this.placa = placa;
        this.marca = marca;
        this.Modelo = modelo;
        this.anio = anio;
        this.color = color;

    }

    public static void deleteAll(EditText txtIdVehiculo, EditText txtPlaca, EditText txtMarca, EditText txtModelo, EditText txtAnio, EditText txtColor) {
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getColor() {
        return color;
    }



    }



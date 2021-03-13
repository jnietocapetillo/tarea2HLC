package com.example.gestionActores.db;

import com.orm.SugarRecord;

//Creamos la clase que se va a almacenar en la base de datos y se hereda de SugarRecord
public class Actores extends SugarRecord {
    private String nombre;
    private String descripcion;
    private String tipo;
    private int img;

    public Actores(){

    }
    public Actores(int img, String nombre, String descripcion, String tipo){
        this.img = img;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() { return tipo; }

    public int getImg() {
        return img;
    }

}

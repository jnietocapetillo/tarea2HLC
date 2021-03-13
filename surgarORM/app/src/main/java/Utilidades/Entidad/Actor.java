package Utilidades.Entidad;

import java.io.Serializable;

public class Actor {
    private String nombre;
    private String descripcion;
    private String tipo;
    private int img;

    public Actor(int img, String nombre, String descripcion, String tipo){
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


package com.example.IntegradoraEstructura.model;

/**
 * Representa un operador del centro de atención.
 * Esta clase sirve como parte del modelo de dominio solicitado.
 */
public class Operador {
    private int id;
    private String nombre;
    private String extension;

    public Operador() {
    }

    public Operador(int id, String nombre, String extension) {
        this.id = id;
        this.nombre = nombre;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

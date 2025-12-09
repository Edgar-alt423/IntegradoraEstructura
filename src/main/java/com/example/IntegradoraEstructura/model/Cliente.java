package com.example.IntegradoraEstructura.model;

public class Cliente {
    private int id;
    private String nombre;
    private String problema;

    public Cliente(int id, String nombre, String problema) {
        this.id = id;
        this.nombre = nombre;
        this.problema = problema;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getProblema() { return problema; }
}
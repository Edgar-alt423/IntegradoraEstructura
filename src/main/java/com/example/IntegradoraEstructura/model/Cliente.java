package com.example.IntegradoraEstructura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String problema;
    private int prioridad;

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaAtencion;


    public Cliente() {
    }

    public Cliente(String nombre, String problema, int prioridad) {
        this.nombre = nombre;
        this.problema = problema;
        this.prioridad = prioridad;
        this.fechaRegistro = LocalDateTime.now();
        this.estado = EstadoCliente.ESPERANDO;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getProblema() { return problema; }
    public int getPrioridad() { return prioridad; }
    public EstadoCliente getEstado() { return estado; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public LocalDateTime getFechaAtencion() { return fechaAtencion; }

    public void setEstado(EstadoCliente estado) { this.estado = estado; }
    public void setFechaAtencion(LocalDateTime fechaAtencion) { this.fechaAtencion = fechaAtencion; }
}
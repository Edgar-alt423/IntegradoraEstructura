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
    private Integer id;
    private String nombre;
    private String telefono;
    private String problema;
    private int prioridad;

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaAtencion;

    public Cliente() {
        // Estado por defecto y fecha de registro si se crea con ctor vacío
        this.estado = EstadoCliente.ESPERANDO;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Cliente(String nombre, String problema, int prioridad) {
        this.nombre = nombre;
        this.problema = problema;
        this.prioridad = prioridad;
        this.fechaRegistro = LocalDateTime.now();
        this.estado = EstadoCliente.ESPERANDO;
    }

    public Cliente(Integer id, String nombre, String telefono, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.prioridad = prioridad;
        this.fechaRegistro = LocalDateTime.now();
        this.estado = EstadoCliente.ESPERANDO;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getProblema() { return problema; }
    public int getPrioridad() { return prioridad; }
    public EstadoCliente getEstado() { return estado; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public LocalDateTime getFechaAtencion() { return fechaAtencion; }

    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setProblema(String problema) { this.problema = problema; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    public void setEstado(EstadoCliente estado) { this.estado = estado; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public void setFechaAtencion(LocalDateTime fechaAtencion) { this.fechaAtencion = fechaAtencion; }
}
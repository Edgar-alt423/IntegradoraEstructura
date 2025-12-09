package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;

// Estructura tipo Pila LIFO
public class PilaHistorial {

    private Cliente[] pila = new Cliente[50];
    private int tope = -1;

    // Push: insertar en la parte superior
    public void push(Cliente c) {
        if (tope < pila.length - 1) {
            pila[++tope] = c;
        }
    }

    // Pop: retirar el último elemento ingresado
    public Cliente pop() {
        if (tope >= 0) {
            return pila[tope--];
        }
        return null;
    }

    // Mostrar pila completa
    public Cliente[] mostrar() {
        return pila;
    }

    public int getTope() {
        return tope;
    }
}

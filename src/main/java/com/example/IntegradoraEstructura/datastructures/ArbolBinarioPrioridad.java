package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;

/**
 * Estructura de Árbol Binario de Búsqueda para organizar clientes por prioridad.
 * La prioridad es un número entero: un número menor indica una mayor prioridad.
 */
public class ArbolBinarioPrioridad {

    /**
     * Nodo interno del árbol.
     */
    private static class Nodo {
        Cliente cliente;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(Cliente cliente) {
            this.cliente = cliente;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;
    private int tamano;

    public ArbolBinarioPrioridad() {
        this.raiz = null;
        this.tamano = 0;
    }

    /**
     * Inserta un cliente en el árbol según su prioridad.
     * @param cliente El cliente a insertar.
     */
    public void insertar(Cliente cliente) {
        this.raiz = insertarRecursivo(this.raiz, cliente);
        this.tamano++;
    }

    private Nodo insertarRecursivo(Nodo actual, Cliente cliente) {
        if (actual == null) {
            return new Nodo(cliente);
        }

        // Un número de prioridad menor es más importante, por lo que va a la izquierda.
        if (cliente.getPrioridad() < actual.cliente.getPrioridad()) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, cliente);
        } else { // Si la prioridad es mayor o igual, va a la derecha (permite duplicados).
            actual.derecho = insertarRecursivo(actual.derecho, cliente);
        }

        return actual;
    }

    /**
     * Devuelve un arreglo de clientes ordenados por prioridad (de mayor a menor importancia).
     * @return Arreglo de clientes en orden de prioridad.
     */
    public Cliente[] obtenerClientesEnOrden() {
        Cliente[] clientesEnOrden = new Cliente[this.tamano];
        recorridoEnOrden(this.raiz, clientesEnOrden, 0);
        return clientesEnOrden;
    }

    /**
     * Realiza un recorrido en orden (in-order) del árbol para llenar el arreglo.
     * @param nodo El nodo actual en el recorrido.
     * @param arreglo El arreglo a llenar.
     * @param indice El índice actual para insertar en el arreglo.
     * @return El siguiente índice a utilizar.
     */
    private int recorridoEnOrden(Nodo nodo, Cliente[] arreglo, int indice) {
        if (nodo != null) {
            indice = recorridoEnOrden(nodo.izquierdo, arreglo, indice);
            
            arreglo[indice] = nodo.cliente;
            indice++;
            
            indice = recorridoEnOrden(nodo.derecho, arreglo, indice);
        }
        return indice;
    }
}

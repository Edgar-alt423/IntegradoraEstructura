package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;

/**
 * Implementación simple de una lista de Clientes sin usar colecciones de Java.
 * - Almacenamiento mediante arreglo interno.
 * - Operaciones básicas: agregar, buscar, eliminar, mostrar.
 * Nota: capacidad inicial fija con crecimiento dinámico sencillo.
 */
public class ListaClientes {

    private Cliente[] arreglo = new Cliente[50];
    private int size = 0;

    // Aumenta la capacidad del arreglo cuando es necesario
    private void ensureCapacity() {
        if (size >= arreglo.length) {
            int nuevaCapacidad = arreglo.length + (arreglo.length >> 1); // x1.5
            if (nuevaCapacidad == arreglo.length) {
                nuevaCapacidad++; // evita quedar igual
            }
            Cliente[] nuevo = new Cliente[nuevaCapacidad];
            for (int i = 0; i < size; i++) {
                nuevo[i] = arreglo[i];
            }
            arreglo = nuevo;
        }
    }

    public void agregar(Cliente c) {
        ensureCapacity();
        arreglo[size++] = c;
    }

    public Cliente buscarPorId(Integer id) {
        if (id == null) return null;
        for (int i = 0; i < size; i++) {
            Cliente cur = arreglo[i];
            if (cur != null && id.equals(cur.getId())) {
                return cur;
            }
        }
        return null;
    }

    public void eliminar(Integer id) {
        if (id == null) return;
        for (int i = 0; i < size; i++) {
            Cliente cur = arreglo[i];
            if (cur != null && id.equals(cur.getId())) {
                // compactar elementos
                for (int j = i; j < size - 1; j++) {
                    arreglo[j] = arreglo[j + 1];
                }
                arreglo[size - 1] = null;
                size--;
                return;
            }
        }
    }

    /**
     * Devuelve una copia acotada al tamaño actual.
     */
    public Cliente[] mostrar() {
        Cliente[] result = new Cliente[size];
        for (int i = 0; i < size; i++) {
            result[i] = arreglo[i];
        }
        return result;
    }

    public int getSize() {
        return size;
    }
}
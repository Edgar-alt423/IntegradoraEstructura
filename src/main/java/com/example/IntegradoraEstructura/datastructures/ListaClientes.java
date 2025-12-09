package com.example.IntegradoraEstructura.datastructures;
import com.example.IntegradoraEstructura.model.Cliente;

// Estructura basada en arreglo dinámico controlado por contador
public class ListaClientes {

    private Cliente[] lista = new Cliente[50];
    private int contador = 0;

    // Agregar: inserta al final del arreglo
    public void agregar(Cliente c) {
        if (contador < lista.length) {
            lista[contador++] = c;
        }
    }

    // Eliminar por id (sustituyendo el último elemento)
    public boolean eliminar(int id) {
        for (int i = 0; i < contador; i++) {
            if (lista[i].getId() == id) {
                lista[i] = lista[contador - 1];
                lista[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    // Buscar por ID
    public Cliente buscar(int id) {
        for (int i = 0; i < contador; i++) {
            if (lista[i].getId() == id) return lista[i];
        }
        return null;
    }

    public Cliente[] mostrar() {
        return lista;
    }

    public int getContador() {
        return contador;
    }
}
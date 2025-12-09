package com.example.IntegradoraEstructura.datastructures;


import com.example.IntegradoraEstructura.model.Cliente;

// Estructura FIFO utilizando un arreglo circular
public class ColaLlamadas {

    // Arreglo donde se almacenan los clientes
    private Cliente[] cola = new Cliente[50];

    // Punteros y contador de elementos
    private int inicio = 0;
    private int fin = -1;
    private int contador = 0;

    // Encolar: insertar al final
    public void encolar(Cliente c) {
        if (contador < cola.length) {
            fin = (fin + 1) % cola.length;
            cola[fin] = c;
            contador++;
        }
    }

    // Desencolar: sacar el primer elemento
    public Cliente desencolar() {
        if (contador > 0) {
            Cliente c = cola[inicio];
            // limpia la ranura para evitar referencias obsoletas
            cola[inicio] = null;
            inicio = (inicio + 1) % cola.length;
            contador--;
            return c;
        }
        return null;
    }

    // Devuelve una copia ordenada (FIFO) con exactamente 'contador' elementos
    public Cliente[] mostrar() {
        Cliente[] resultado = new Cliente[contador];
        for (int i = 0; i < contador; i++) {
            int idx = (inicio + i) % cola.length;
            resultado[i] = cola[idx];
        }
        return resultado;
    }

    // Retorna cantidad de elementos almacenados
    public int getContador() {
        return contador;
    }
}

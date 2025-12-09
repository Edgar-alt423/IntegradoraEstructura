package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArbolBinarioPrioridadTest {

    private ArbolBinarioPrioridad arbol;

    @BeforeEach
    public void setUp() {
        arbol = new ArbolBinarioPrioridad();
    }

    @Test
    public void testArbolVacio() {
        Cliente[] clientes = arbol.obtenerClientesEnOrden();
        assertEquals(0, clientes.length);
    }

    @Test
    public void testInsertarYRecorridoEnOrden() {
        Cliente c2 = new Cliente(1, "Prioridad Media", "Problema B", 2);
        Cliente c1 = new Cliente(2, "Prioridad Alta", "Problema A", 1);
        Cliente c3 = new Cliente(3, "Prioridad Baja", "Problema C", 3);

        arbol.insertar(c2);
        arbol.insertar(c1);
        arbol.insertar(c3);

        Cliente[] clientes = arbol.obtenerClientesEnOrden();
        
        assertEquals(3, clientes.length);
        assertEquals(1, clientes[0].getPrioridad()); // Prioridad más alta (menor número) primero
        assertEquals("Prioridad Alta", clientes[0].getNombre());
        
        assertEquals(2, clientes[1].getPrioridad());
        assertEquals("Prioridad Media", clientes[1].getNombre());

        assertEquals(3, clientes[2].getPrioridad());
        assertEquals("Prioridad Baja", clientes[2].getNombre());
    }

    @Test
    public void testInsertarPrioridadesDuplicadas() {
        Cliente c1 = new Cliente(1, "Cliente A", "Problema", 2);
        Cliente c2 = new Cliente(2, "Cliente B", "Problema", 1);
        Cliente c3 = new Cliente(3, "Cliente C", "Problema", 2); // Misma prioridad que c1

        arbol.insertar(c1);
        arbol.insertar(c2);
        arbol.insertar(c3);

        Cliente[] clientes = arbol.obtenerClientesEnOrden();
        assertEquals(3, clientes.length);

        // El orden exacto de prioridades duplicadas puede variar (derecha o izquierda),
        // pero el ordenamiento general debe ser correcto.
        assertEquals(1, clientes[0].getPrioridad());
        assertEquals(2, clientes[1].getPrioridad());
        assertEquals(2, clientes[2].getPrioridad());
    }
    
    @Test
    public void testOrdenamientoComplejo() {
        arbol.insertar(new Cliente(1, "ID 1", "P", 5));
        arbol.insertar(new Cliente(2, "ID 2", "P", 2));
        arbol.insertar(new Cliente(3, "ID 3", "P", 4));
        arbol.insertar(new Cliente(4, "ID 4", "P", 1));
        arbol.insertar(new Cliente(5, "ID 5", "P", 3));

        Cliente[] clientes = arbol.obtenerClientesEnOrden();
        
        assertEquals(5, clientes.length);
        assertEquals(1, clientes[0].getPrioridad());
        assertEquals(2, clientes[1].getPrioridad());
        assertEquals(3, clientes[2].getPrioridad());
        assertEquals(4, clientes[3].getPrioridad());
        assertEquals(5, clientes[4].getPrioridad());
    }
}

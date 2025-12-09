package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaClientesTest {

    private ListaClientes lista;

    @BeforeEach
    public void setUp() {
        lista = new ListaClientes();
    }

    @Test
    public void testAgregarYBuscarCliente() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        lista.agregar(cliente1);
        assertEquals(1, lista.getContador());
        
        Cliente encontrado = lista.buscar(1);
        assertNotNull(encontrado);
        assertEquals("Cliente Uno", encontrado.getNombre());
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        Cliente cliente2 = new Cliente(2, "Cliente Dos", "Problema B", 2);
        lista.agregar(cliente1);
        lista.agregar(cliente2);
        
        assertEquals(2, lista.getContador());
        
        boolean eliminado = lista.eliminar(1);
        assertTrue(eliminado);
        assertEquals(1, lista.getContador());
        assertNull(lista.buscar(1));
        assertNotNull(lista.buscar(2)); // Asegurarse que el otro cliente sigue ahí
    }

    @Test
    public void testBuscarClienteNoExistente() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        lista.agregar(cliente1);
        
        Cliente encontrado = lista.buscar(99);
        assertNull(encontrado);
    }
    
    @Test
    public void testEliminarClienteInexistente() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        lista.agregar(cliente1);
        
        boolean eliminado = lista.eliminar(99);
        assertFalse(eliminado);
        assertEquals(1, lista.getContador());
    }

    @Test
    public void testAgregarHastaElLimite() {
        for (int i = 0; i < 50; i++) {
            lista.agregar(new Cliente(i, "Cliente " + i, "Problema", 1));
        }
        assertEquals(50, lista.getContador());

        // Intentar agregar uno más
        lista.agregar(new Cliente(50, "Cliente Extra", "Overflow", 1));
        assertEquals(50, lista.getContador()); // El contador no debería cambiar
        assertNull(lista.buscar(50)); // El cliente extra no debería existir
    }
}

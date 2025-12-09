package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColaLlamadasTest {

    private ColaLlamadas cola;

    @BeforeEach
    public void setUp() {
        cola = new ColaLlamadas();
    }

    @Test
    public void testEncolarYDesencolarSimple() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        Cliente cliente2 = new Cliente(2, "Cliente Dos", "Problema B", 2);

        cola.encolar(cliente1);
        cola.encolar(cliente2);
        assertEquals(2, cola.getContador());

        Cliente atendido1 = cola.desencolar();
        assertNotNull(atendido1);
        assertEquals(1, atendido1.getId());
        assertEquals(1, cola.getContador());

        Cliente atendido2 = cola.desencolar();
        assertNotNull(atendido2);
        assertEquals(2, atendido2.getId());
        assertEquals(0, cola.getContador());
    }

    @Test
    public void testDesencolarDeColaVacia() {
        assertNull(cola.desencolar());
        assertEquals(0, cola.getContador());
    }

    @Test
    public void testEncolarHastaElLimite() {
        for (int i = 0; i < 50; i++) {
            cola.encolar(new Cliente(i, "Cliente " + i, "Problema", 1));
        }
        assertEquals(50, cola.getContador());

        // Intentar encolar uno más en una cola llena
        cola.encolar(new Cliente(50, "Cliente Extra", "Overflow", 1));
        assertEquals(50, cola.getContador()); // El tamaño no debe cambiar
    }
    
    @Test
    public void testColaCircular() {
        // Llenar la cola
        for (int i = 0; i < 50; i++) {
            cola.encolar(new Cliente(i, "Cliente " + i, "Problema", 1));
        }

        // Vaciar algunos elementos para hacer espacio al principio
        for (int i = 0; i < 10; i++) {
            Cliente c = cola.desencolar();
            assertEquals(i, c.getId());
        }
        assertEquals(40, cola.getContador());

        // Encolar nuevos elementos, que deberían ir al principio del arreglo (circular)
        for (int i = 50; i < 60; i++) {
            cola.encolar(new Cliente(i, "Cliente " + i, "Problema", 2));
        }
        assertEquals(50, cola.getContador());

        // Verificar que el próximo en salir sea el correcto
        Cliente proximo = cola.desencolar();
        assertNotNull(proximo);
        assertEquals(10, proximo.getId()); // El cliente con ID 10 debe ser el siguiente
    }
}

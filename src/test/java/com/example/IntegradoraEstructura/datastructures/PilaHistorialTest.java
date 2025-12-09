package com.example.IntegradoraEstructura.datastructures;

import com.example.IntegradoraEstructura.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilaHistorialTest {

    private PilaHistorial pila;

    @BeforeEach
    public void setUp() {
        pila = new PilaHistorial();
    }

    @Test
    public void testPushYPopSimple() {
        Cliente cliente1 = new Cliente(1, "Cliente Uno", "Problema A", 1);
        Cliente cliente2 = new Cliente(2, "Cliente Dos", "Problema B", 2);

        pila.push(cliente1);
        pila.push(cliente2);
        assertEquals(1, pila.getTope()); // El tope es el índice, así que es 1

        Cliente sacado1 = pila.pop();
        assertNotNull(sacado1);
        assertEquals(2, sacado1.getId()); // Debe salir el último en entrar
        assertEquals(0, pila.getTope());

        Cliente sacado2 = pila.pop();
        assertNotNull(sacado2);
        assertEquals(1, sacado2.getId());
        assertEquals(-1, pila.getTope());
    }

    @Test
    public void testPopDePilaVacia() {
        assertNull(pila.pop());
        assertEquals(-1, pila.getTope());
    }

    @Test
    public void testPushHastaElLimite() {
        for (int i = 0; i < 50; i++) {
            pila.push(new Cliente(i, "Cliente " + i, "Problema", 1));
        }
        assertEquals(49, pila.getTope());

        // Intentar hacer push uno más
        pila.push(new Cliente(50, "Cliente Extra", "Overflow", 1));
        assertEquals(49, pila.getTope()); // El tope no debe cambiar

        // Verificar que el último elemento sigue siendo el correcto
        Cliente ultimo = pila.pop();
        assertNotNull(ultimo);
        assertEquals(49, ultimo.getId());
    }
}

package com.example.IntegradoraEstructura.service;

import org.springframework.stereotype.Service;

import com.example.IntegradoraEstructura.datastructures.ArbolBinarioPrioridad;
import com.example.IntegradoraEstructura.datastructures.ColaLlamadas;
import com.example.IntegradoraEstructura.datastructures.ListaClientes;
import com.example.IntegradoraEstructura.datastructures.PilaHistorial;
import com.example.IntegradoraEstructura.model.Cliente;

import jakarta.annotation.PostConstruct;

/**
 * Servicio que orquesta las estructuras de datos y expone operaciones de negocio.
 * Importante: No usa colecciones de Java para implementar las estructuras,
 * solamente coordina las estructuras ya implementadas en el proyecto.
 */
@Service
public class SimuladorService {

    private final ListaClientes listaClientes = new ListaClientes();
    private final ColaLlamadas colaLlamadas = new ColaLlamadas();
    private final PilaHistorial pilaHistorial = new PilaHistorial();

    /**
     * Agrega un cliente al almacenamiento general (lista/arreglo).
     */
    public void agregarCliente(int id, String nombre, String telefono, int prioridad) {
        Cliente c = new Cliente();
        // Se asume que la entidad Cliente posee setters compatibles con estos nombres.
        c.setId(id);
        c.setNombre(nombre);
        c.setTelefono(telefono);
        c.setPrioridad(prioridad);
        listaClientes.agregar(c);
    }

    /**
     * Elimina cliente por id.
     */
    public boolean eliminarCliente(int id) {
        return listaClientes.eliminar(id);
    }

    /**
     * Busca un cliente por id en el almacenamiento general.
     */
    public Cliente buscarCliente(int id) {
        return listaClientes.buscar(id);
    }

    /**
     * Encola una llamada por id de cliente (FIFO).
     */
    public boolean encolarLlamadaPorClienteId(int idCliente) {
        Cliente c = buscarCliente(idCliente);
        if (c != null) {
            colaLlamadas.encolar(c);
            return true;
        }
        return false;
    }

    /**
     * Atiende la siguiente llamada (desencola y envía a historial - pila LIFO).
     */
    public Cliente atenderSiguienteLlamada() {
        Cliente c = colaLlamadas.desencolar();
        if (c != null) {
            pilaHistorial.push(c);
        }
        return c;
    }

    /**
     * Devuelve un arreglo con los clientes ordenados por prioridad usando el árbol binario.
     * Se reconstruye el árbol a partir del almacenamiento actual.
     */
    public Cliente[] obtenerClientesOrdenadosPorPrioridad() {
        ArbolBinarioPrioridad arbol = new ArbolBinarioPrioridad();
        Cliente[] todos = listaClientes.mostrar();
        int n = listaClientes.getContador();
        for (int i = 0; i < n; i++) {
            arbol.insertar(todos[i]);
        }
        return arbol.obtenerClientesEnOrden();
    }

    /**
     * Snapshot del estado del simulador para la vista.
     */
    public EstadoSimulador obtenerEstado() {
        EstadoSimulador e = new EstadoSimulador();
        // Lista de clientes
        int totalClientes = listaClientes.getContador();
        e.clientes = copiarHasta(listaClientes.mostrar(), totalClientes);

        // Cola de llamadas (arreglo circular expuesto como arreglo con contador)
        int totalCola = colaLlamadas.getContador();
        e.cola = copiarHasta(colaLlamadas.mostrar(), totalCola);

        // Historial (pila)
        int totalPila = pilaHistorial.getTope() + 1;
        e.pila = copiarHasta(pilaHistorial.mostrar(), totalPila);

        // Prioridad (árbol)
        Cliente[] porPrioridad = obtenerClientesOrdenadosPorPrioridad();
        e.prioridad = copiarHasta(porPrioridad, porPrioridad != null ? porPrioridad.length : 0);

        return e;
    }

    private Cliente[] copiarHasta(Cliente[] origen, int hasta) {
        if (hasta <= 0) return new Cliente[0];
        Cliente[] r = new Cliente[hasta];
        for (int i = 0; i < hasta; i++) {
            r[i] = origen[i];
        }
        return r;
    }

    /**
     * Carga de datos de ejemplo para poder probar rápidamente la interfaz.
     */
    @PostConstruct
    public void seedDemo() {
        if (listaClientes.getContador() == 0) {
            agregarCliente(1, "Ana López", "555-1111", 2);
            agregarCliente(2, "Juan Pérez", "555-2222", 1);
            agregarCliente(3, "María Ruiz", "555-3333", 3);
        }
    }

    /**
     * Estructura de datos de sólo lectura para la vista.
     */
    public static class EstadoSimulador {
        public Cliente[] clientes;
        public Cliente[] cola;
        public Cliente[] pila;
        public Cliente[] prioridad;
    }
}

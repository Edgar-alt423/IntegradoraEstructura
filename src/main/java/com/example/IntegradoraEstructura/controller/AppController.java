package com.example.IntegradoraEstructura.controller;

import com.example.IntegradoraEstructura.model.Cliente;
import com.example.IntegradoraEstructura.datastructures.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controlador principal de la aplicación web.
 * Gestiona las interacciones del usuario y la comunicación entre la vista y las estructuras de datos.
 */
@Controller
public class AppController {

    // Almacenamiento en memoria para la lista principal de clientes
    private final List<Cliente> lista = new ArrayList<>();
    private final AtomicInteger idSeq = new AtomicInteger(1);

    private ColaLlamadas cola = new ColaLlamadas();
    private PilaHistorial pila = new PilaHistorial();
    private ArbolBinarioPrioridad arbol = new ArbolBinarioPrioridad();

    /**
     * Mapea la ruta "/app". Prepara los datos de todas las estructuras
     * y los envía al modelo para que la vista 'index.html' los renderice.
     * @param model El modelo al que se añaden los atributos para la vista.
     * @return El nombre de la plantilla de la vista (index).
     */
    @GetMapping("/app")
    public String index(Model model) {
        List<Cliente> clientes = lista;
        model.addAttribute("lista", clientes);
        model.addAttribute("cola", cola.mostrar());
        model.addAttribute("pila", pila.mostrar());
        model.addAttribute("arbol", arbol.obtenerClientesEnOrden());
        model.addAttribute("contadorLista", clientes.size());
        model.addAttribute("contadorCola", cola.getContador());
        model.addAttribute("topePila", pila.getTope());
        return "index";
    }

    /**
     * Procesa la solicitud para registrar un nuevo cliente (llamada).
     * Recibe los datos del formulario, crea un nuevo objeto Cliente y lo agrega
     * a la lista general, la cola de llamadas y el árbol de prioridades.
     * @param nombre El nombre del cliente.
     * @param problema La descripción del problema del cliente.
     * @param prioridad El nivel de prioridad de la llamada.
     * @return Una redirección a la página principal para mostrar el estado actualizado.
     */
    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre,
                            @RequestParam String problema,
                            @RequestParam int prioridad) {

        Cliente c = new Cliente(nombre, problema, prioridad);
        c.setId(idSeq.getAndIncrement()); // Asigna ID incremental en memoria
        lista.add(c);
        cola.encolar(c);
        arbol.insertar(c);

        return "redirect:/app";
    }

    /**
     * Procesa la solicitud para atender al siguiente cliente en la cola.
     * Saca un cliente de la cola (FIFO) y lo mete en la pila del historial (LIFO).
     * @return Una redirección a la página principal para mostrar el estado actualizado.
     */
    @PostMapping("/atender")
    public String atender() {
        Cliente c = cola.desencolar();
        if (c != null) pila.push(c);
        return "redirect:/app";
    }
}

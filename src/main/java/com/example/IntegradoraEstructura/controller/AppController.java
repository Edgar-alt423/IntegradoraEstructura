package com.example.IntegradoraEstructura.controller;

import com.example.IntegradoraEstructura.model.Cliente;
import com.example.IntegradoraEstructura.datastructures.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private ListaClientes lista = new ListaClientes();
    private ColaLlamadas cola = new ColaLlamadas();
    private PilaHistorial pila = new PilaHistorial();
    private int idAuto = 1;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lista", lista.mostrar());
        model.addAttribute("cola", cola.mostrar());
        model.addAttribute("pila", pila.mostrar());
        model.addAttribute("contadorLista", lista.getContador());
        model.addAttribute("contadorCola", cola.getContador());
        model.addAttribute("topePila", pila.getTope());
        return "index";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre,
                            @RequestParam String problema) {

        Cliente c = new Cliente(idAuto++, nombre, problema);
        lista.agregar(c);
        cola.encolar(c);

        return "redirect:/";
    }

    @PostMapping("/atender")
    public String atender() {
        Cliente c = cola.desencolar();
        if (c != null) pila.push(c);
        return "redirect:/";
    }
}

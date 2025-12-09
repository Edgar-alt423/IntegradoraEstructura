package com.example.IntegradoraEstructura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.IntegradoraEstructura.model.Cliente;
import com.example.IntegradoraEstructura.service.SimuladorService;

@Controller
public class SimuladorController {

    private final SimuladorService simuladorService;

    public SimuladorController(SimuladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("estado", simuladorService.obtenerEstado());
        return "simulador";
    }

    @PostMapping("/clientes/agregar")
    public String agregarCliente(
            @RequestParam int id,
            @RequestParam String nombre,
            @RequestParam String telefono,
            @RequestParam int prioridad,
            Model model) {
        simuladorService.agregarCliente(id, nombre, telefono, prioridad);
        model.addAttribute("estado", simuladorService.obtenerEstado());
        return "simulador";
    }

    @PostMapping("/clientes/eliminar")
    public String eliminarCliente(@RequestParam int id, Model model) {
        simuladorService.eliminarCliente(id);
        model.addAttribute("estado", simuladorService.obtenerEstado());
        return "simulador";
    }

    @PostMapping("/llamadas/encolar")
    public String encolarLlamada(@RequestParam("idCliente") int idCliente, Model model) {
        simuladorService.encolarLlamadaPorClienteId(idCliente);
        model.addAttribute("estado", simuladorService.obtenerEstado());
        return "simulador";
    }

    @PostMapping("/llamadas/atender")
    public String atender(Model model) {
        Cliente atendido = simuladorService.atenderSiguienteLlamada();
        model.addAttribute("atendido", atendido);
        model.addAttribute("estado", simuladorService.obtenerEstado());
        return "simulador";
    }
}

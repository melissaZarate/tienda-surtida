package com.tiendasurtida.controller;

import com.tiendasurtida.service.HistorialPrecioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/historial-precios")
public class HistorialPrecioController {

    private final HistorialPrecioService historialPrecioService;

    public HistorialPrecioController(HistorialPrecioService historialPrecioService) {

        this.historialPrecioService = historialPrecioService;
    }


    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "historiales",
                historialPrecioService.listarHistorial());

        model.addAttribute("totalCambios",
                historialPrecioService.contarTodos());

        model.addAttribute("totalCompras",
                historialPrecioService.contarCompras());

        model.addAttribute("totalAjustes",
                historialPrecioService.contarAjustesManuales());

        return "historial-precio/lista";
    }
}

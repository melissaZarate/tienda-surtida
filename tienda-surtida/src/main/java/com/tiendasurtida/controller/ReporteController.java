package com.tiendasurtida.controller;

import com.tiendasurtida.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
    @RequestMapping("/reportes")
    public class ReporteController {

        private final ReporteService reporteService;

        public ReporteController(ReporteService reporteService) {
            this.reporteService = reporteService;
        }

        @GetMapping("/financiero")
        public String reporteFinanciero(
                @RequestParam(required = false)
                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
                @RequestParam(required = false)
                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fin,
                Model model) {
            if(inicio!=null && fin!=null){
                model.addAttribute("reporte", reporteService.reporteFinanciero(inicio, fin));
                model.addAttribute("inicio", inicio);
                model.addAttribute("fin", fin);

            }

            return "reportes/financiero";
        }

        @GetMapping("/stock")
        public String stockBajo(Model model) {

            model.addAttribute("productos", reporteService.reporteStockBajo());

            return "reportes/stock";
        }
    @GetMapping("/productos-mas-vendidos")
    public String productosMasVendidos(
            @RequestParam(required = false) //se caga en null
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fin,

            Model model) {

        if (inicio != null && fin != null) {

            model.addAttribute("productos", reporteService.obtenerProductosMasVendidos(inicio, fin));

            model.addAttribute("inicio", inicio);
            model.addAttribute("fin", fin);
        }

        return "reportes/productos-mas-vendidos";
    }
}


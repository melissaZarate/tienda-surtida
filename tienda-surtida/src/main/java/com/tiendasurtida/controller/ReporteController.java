package com.tiendasurtida.controller;

import com.tiendasurtida.dto.RentabilidadProductoDTO;
import com.tiendasurtida.dto.ReporteVentasDiariasDTO;
import com.tiendasurtida.dto.ReporteVentasResumenDTO;
import com.tiendasurtida.service.ReporteService;
import com.tiendasurtida.service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
    @RequestMapping("/reportes")
    public class ReporteController {

        private final ReporteService reporteService;
        private final VentaService ventaService;

    public ReporteController(ReporteService reporteService, VentaService ventaService) {
        this.reporteService = reporteService;
        this.ventaService = ventaService;
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
            @RequestParam(required = false) //se carga en null
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
    @GetMapping("/rentabilidad")
    public String verRentabilidad(
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fin,
            Model model) {

        //  Fechas por defecto (últimos 7 días)
        if (inicio == null) inicio = LocalDate.now().minusDays(7);
        if (fin == null) fin = LocalDate.now();

        LocalDateTime fechaInicio = inicio.atStartOfDay();
        LocalDateTime fechaFin = fin.atTime(23, 59, 59);

        // Datos principales del reporte
        List<RentabilidadProductoDTO> rentabilidad =
                reporteService.obtenerRentabilidadProductos(fechaInicio, fechaFin);

        // KPIs generales
        BigDecimal   totalGanancia = rentabilidad.stream()
                .map(RentabilidadProductoDTO::getGanancia)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalIngreso = rentabilidad.stream()
                .map(RentabilidadProductoDTO::getIngresoGenerado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Long totalUnidades = rentabilidad.stream()
                .mapToLong(RentabilidadProductoDTO::getCantidadVendida)
                .sum();

        // Estado general del negocio
        String estado;

        if (totalGanancia.compareTo(BigDecimal.ZERO) > 0) {
            estado = "POSITIVO";
        } else if (totalGanancia.compareTo(BigDecimal.ZERO) < 0) {
            estado = "NEGATIVO";
        } else {
            estado = "NEUTRO";
        }

        //  Enviar a la vista
        model.addAttribute("rentabilidad", rentabilidad);
        model.addAttribute("totalGanancia", totalGanancia);
        model.addAttribute("totalIngreso", totalIngreso);
        model.addAttribute("totalUnidades", totalUnidades);
        model.addAttribute("estado", estado);

        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);

        return "reportes/rentabilidad";
    }
    @GetMapping("/ventas-diarias")
    public String reporteVentasDiarias(

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin,

            Model model) {

        if (fechaInicio == null) {
            fechaInicio = LocalDate.now();
        }

        if (fechaFin == null) {
            fechaFin = LocalDate.now();
        }

        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.atTime(23, 59, 59);

        // resuemn
           ReporteVentasResumenDTO reporte = ventaService.obtenerReporteVentasDiarias(inicio, fin);

        model.addAttribute("reporte", reporte);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "reportes/ventas-diarias";
    }
}


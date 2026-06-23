
package com.tiendasurtida.controller;

import com.tiendasurtida.dto.CajaDTO;
import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.service.CajaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/caja")
public class CajaController {

    private final CajaService cajaService;

    public CajaController(CajaService cajaService) {
        this.cajaService = cajaService;
    }
    //mostrar cajaActual
    @GetMapping
    public String cajaActual(Model model) {

        Caja caja = cajaService.obtenerCajaAbierta(); //busca la caja abierta yla envia a thymeleaf

        model.addAttribute("caja", caja);

        return "caja/actual";
    }
    //formulariuo abrir caja
    @GetMapping("/abrir")
    public String formularioAbrirCaja(Model model) {

        model.addAttribute("cajaDTO", new CajaDTO()); //creamos un objeto vaciopara que thymeleaf pueda llenar

        return "caja/abrir";
    }
    //guardar apertura de caja en abrir
    @PostMapping("/abrir")
    public String abrirCaja(@ModelAttribute CajaDTO cajaDTO) {

        try {
            cajaService.abrirCaja(cajaDTO);

        } catch (RuntimeException e) {
            return "redirect:/caja?error=" + e.getMessage();
        }

        return "redirect:/caja?success=abierta";
    }
    //cerrar caja
    @PostMapping("/cerrar")
    public String cerrarCaja() {

        try {
            cajaService.cerrarCaja(); //llamar al serviceimp ysumarr las ventas,comroas y  clcular el saldo

        } catch (RuntimeException e) {
            return "redirect:/caja?error=" + e.getMessage();
        }

        return "redirect:/caja?success=cerrada";
    }
    //historial de cajas
    @GetMapping("/historial")
    public String historial(Model model) {

        model.addAttribute(
                "cajas",
                cajaService.listarCajas()
        );

        return "caja/historial";
    }

}
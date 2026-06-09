package com.tiendasurtida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    @GetMapping("/test")
    public String ventasTest() {
        return "ventas";
    }
}
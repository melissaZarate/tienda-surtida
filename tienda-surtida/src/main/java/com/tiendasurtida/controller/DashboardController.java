package com.tiendasurtida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String inicio() {

        return "dashboard/index";
    }
   /* public String inicio(Model model) {
        model.addAttribute("contenido","dashboard/index :: contenido");
        return "layout/base";
    }*/
}
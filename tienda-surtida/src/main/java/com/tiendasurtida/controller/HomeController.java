package com.tiendasurtida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        String role = auth.getAuthorities().toString();

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        return "dashboard";
    }
}

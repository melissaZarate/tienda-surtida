package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Proveedor;
import com.tiendasurtida.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("proveedores", service.listarProveedores());
        model.addAttribute("proveedor", new Proveedor());

        return "proveedor/lista";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("proveedor") Proveedor proveedor,
            BindingResult result,
            Model model,
            RedirectAttributes redirect) {

        if (result.hasErrors()) {

            model.addAttribute("proveedores",
                    service.listarProveedores());

            return "proveedor/lista";
        }

        service.guardarProveedor(proveedor);

        redirect.addFlashAttribute(
                "mensaje",
                "Proveedor guardado correctamente");

        redirect.addFlashAttribute(
                "tipo",
                "success");

        return "redirect:/proveedores";
    }
    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "nombre", required = false) String nombre,
                         Model model) {

        model.addAttribute("proveedores",
                service.buscarPorNombre(nombre));

        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("nombre", nombre);

        return "proveedor/lista";
    }
}
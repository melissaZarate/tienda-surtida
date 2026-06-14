package com.tiendasurtida.controller;

import com.tiendasurtida.entity.UnidadMedida;
import com.tiendasurtida.service.UnidadMedidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/unidades")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService service;

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("unidades", service.listarUnidades());
        model.addAttribute("unidad", new UnidadMedida());

        return "unidad/lista";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("unidad") UnidadMedida unidad,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirect) {

        if (result.hasErrors()) {
            model.addAttribute("unidades", service.listarUnidades());
            return "unidad/lista";
        }

        service.guardar(unidad);

        redirect.addFlashAttribute("mensaje", "Unidad guardada correctamente");
        redirect.addFlashAttribute("tipo", "success");

        return "redirect:/unidades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        model.addAttribute("unidad", service.buscarPorId(id));
        model.addAttribute("unidades", service.listarUnidades());

        return "unidad/lista";
    }
}
package com.tiendasurtida.controller;
import com.tiendasurtida.entity.Categoria;
import com.tiendasurtida.service.CategoriaService;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model){

        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoria",new Categoria());

        return "categoria/lista";
    }
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirect) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "categoria/lista";
        }

        categoriaService.guardarCategoria(categoria);

        redirect.addFlashAttribute("mensaje", "Categoría guardada correctamente");
        redirect.addFlashAttribute("tipo", "success");

        return "redirect:/categorias";
    }
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("categoria") Categoria categoria,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            model.addAttribute("categorias", categoriaService.listarCategorias());
            model.addAttribute("categoria", categoria);

            return "categoria/lista";
        }

        categoriaService.guardarCategoria(categoria);

        redirectAttributes.addFlashAttribute("mensaje", "Categoría actualizada correctamente");
        redirectAttributes.addFlashAttribute("tipo", "success");

        return "redirect:/categorias";
    }
}
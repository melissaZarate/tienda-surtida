
package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.service.UsuarioService;
import com.tiendasurtida.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.tiendasurtida.entity.rol;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;



    public UsuarioController(UsuarioService usuarioService,RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    // listar ussuario
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/lista";
    }

    // mostrar form
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        //para el select de roles
        model.addAttribute("roles", rolService.listarRolesAsignables());
        return "usuarios/formulario";
    }

    // guardar usaurio
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario,
                                 RedirectAttributes redirectAttributes) {

        try {
            usuarioService.guardarUsuario(usuario);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Usuario guardado correctamente."
            );

            return "redirect:/usuarios";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error", e.getMessage());
            redirectAttributes.addFlashAttribute(
                    "usuario", usuario);


            return "redirect:/usuarios/nuevo";
        }
    }
    //editar usuario
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
       // model.addAttribute("roles",rolService.listarRoles());
        model.addAttribute("roles", rolService.listarRolesAsignables());
        return "usuarios/formulario";
    }
    //eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {

        usuarioService.eliminarUsuario(id);

        redirectAttributes.addFlashAttribute(
                "warning",
                "Usuario desactivado correctamente."
        );

        return "redirect:/usuarios";
    }
    //activar o desactivar usuario
    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {

        try {
            boolean estado = usuarioService.cambiarEstado(id);

            if (estado) {
                redirectAttributes.addFlashAttribute("success", "Usuario activado correctamente.");
            } else {
                redirectAttributes.addFlashAttribute("warning", "Usuario desactivado correctamente.");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/usuarios";
    }
}
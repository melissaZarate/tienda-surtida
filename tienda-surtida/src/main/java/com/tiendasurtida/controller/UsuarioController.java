
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
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios/formulario";
    }

    // guardar usaurio
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }
    //editar usuario
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }
    //eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    //activar o desactivar usuario
    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        usuario.setEstadoUsuario(!usuario.getEstadoUsuario());
        usuarioService.guardarUsuario(usuario);

        return "redirect:/usuarios";
    }
}
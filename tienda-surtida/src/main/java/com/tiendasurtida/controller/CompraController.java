package com.tiendasurtida.controller;
import com.tiendasurtida.entity.Compra;
import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.entity.Proveedor;
import com.tiendasurtida.service.CompraService;
import com.tiendasurtida.service.ProveedorService;
import com.tiendasurtida.service.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("/compras")
public class CompraController {
//constructos
    private final CompraService compraService;
    private final ProveedorService proveedorService;
    private final UsuarioService usuarioService;

    public CompraController(CompraService compraService,
                            ProveedorService proveedorService,
                            UsuarioService usuarioService) {

        this.compraService = compraService;
        this.proveedorService = proveedorService;
        this.usuarioService = usuarioService;
    }
    //lista comora
    @GetMapping
    public String listarCompras(Model model) {

        model.addAttribute("compras",
                compraService.listarTodas());

        return "compras/lista";
    }
    //ostrar formkario nueva compora
    @GetMapping("/nuevo")
    public String nuevaCompra(Model model) {

        model.addAttribute("compra", new Compra());

        model.addAttribute("proveedores",
                proveedorService.listarProveedores());

        return "compras/form";
    }
    //gyardar compra
    @PostMapping("/guardar")
    public String guardarCompra(@ModelAttribute Compra compra,
                                @RequestParam Long idProveedor, RedirectAttributes redirectAttributes) {

        Proveedor proveedor =
                proveedorService.buscarPorId(idProveedor);

        compra.setProveedor(proveedor);

        Authentication auth =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String username = auth.getName();

        Usuario usuario =
                usuarioService.buscarPorUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException("Usuario no encontrado"));

        compra.setUsuario(usuario);

        compraService.crearCompra(compra);
        //mensajes de alerta
        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Compra registrada correctamente"
        );

        redirectAttributes.addFlashAttribute(
                "tipo",
                "success"
        );
        //para errores
      /*          "mensaje",
                "Ocurrió un error al registrar la compra"
        );

        redirectAttributes.addFlashAttribute(
                "tipo",
                "danger"
        );*/

        return "redirect:/compras";
    }

}
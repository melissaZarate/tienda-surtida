package com.tiendasurtida.controller;

import com.tiendasurtida.entity.*;

import com.tiendasurtida.service.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;
    private final ProveedorService proveedorService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final UnidadCompraService unidadCompraService;

    public CompraController(CompraService compraService, ProveedorService proveedorService, UsuarioService usuarioService, ProductoService productoService, UnidadCompraService unidadCompraService) {
        this.compraService = compraService;
        this.proveedorService = proveedorService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.unidadCompraService = unidadCompraService;
    }

    // LISTAR COMPRAS
    @GetMapping
    public String listarCompras(Model model) {

        model.addAttribute(
                "compras",
                compraService.listarTodas()
        );

        return "compras/lista";
    }

    // FORMULARIO NUEVA COMPRA
    @GetMapping("/nuevo")
    public String nuevaCompra(Model model) {


            model.addAttribute("compra", new Compra());


            model.addAttribute("proveedores", proveedorService.listarProveedoresActivos());

            return "compras/form";

    }


    // GUARDAR COMPRA
    @PostMapping("/guardar")
    public String guardarCompra(
            @ModelAttribute Compra compra,
            @RequestParam Long idProveedor,
            RedirectAttributes redirectAttributes) {
        try {

            Proveedor proveedor = proveedorService.buscarPorId(idProveedor);

            compra.setProveedor(proveedor);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Usuario usuario = usuarioService.buscarPorUsername(username).orElseThrow(() ->
                    new RuntimeException("Usuario no encontrado"));

            compra.setUsuario(usuario);
            Compra compraGuardada = compraService.crearCompra(compra);
            redirectAttributes.addFlashAttribute("mensaje", "Compra registrada correctamente");
            redirectAttributes.addFlashAttribute("tipo", "success");
            return "redirect:/compras/" + compraGuardada.getIdCompra();
        }   catch (RuntimeException e) {
             return "redirect:/compras/nuevo?error=" + e.getMessage();
                }
    }

    // VER DETALLE DE COMPRA
    @GetMapping("/{id}")
    public String verCompra(
            @PathVariable Long id,
            Model model) {

        Compra compra =
                compraService.obtenerPorId(id);

        model.addAttribute("compra", compra);

        model.addAttribute("productos", productoService.listarProductosActivos());
        model.addAttribute("unidadesCompra", unidadCompraService.listarTodas());

        model.addAttribute("detalles", compra.getDetalles());

        model.addAttribute(
                "detalleCompra",
                new DetalleCompra()
        );

        return "compras/detalle";
    }

    // AGREGAR DETALLE
    @PostMapping("/{id}/detalle")
    public String agregarDetalle(
            @PathVariable Long id,

            @RequestParam Long idProducto,

            @RequestParam Integer cantidad,

            @RequestParam Double precioTotal,

            @RequestParam Double precioVentaFinal,
            //añadimos kos nuevo atriutos
            @RequestParam Long idUnidadCompra,
            @RequestParam Integer unidadesIngresadasDetalle,
            //para capturar fechade vencimiennto
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate fechaVencimiento,

            RedirectAttributes redirectAttributes) {

        DetalleCompra detalle = new DetalleCompra();

        detalle.setCantidadDetalle(cantidad);



        //nuevis cambuios
        UnidadCompra unidadCompra= unidadCompraService.buscarPorId(idUnidadCompra);
        detalle.setUnidadCompra(unidadCompra);
        detalle.setUnidadesIngresadasDetalle(unidadesIngresadasDetalle);

        detalle.setPrecioTotalDetalle(BigDecimal.valueOf(precioTotal));
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);

        detalle.setProducto(producto);

        compraService.agregarDetalle(
                id, detalle, BigDecimal.valueOf(precioVentaFinal), fechaVencimiento
        );

        redirectAttributes.addFlashAttribute(
                "mensaje", "Producto agregado correctamente a la compra.");

        redirectAttributes.addFlashAttribute("tipo", "success");

        return "redirect:/compras/" + id;
    }
    @PostMapping("/finalizar/{id}")
    public String finalizarCompra(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {

        Compra compra = compraService.obtenerPorId(id);

        // cambiar estado

        compra.setEstadoCompra("FINALIZADA");

        compraService.actualizarCompra(compra); // o save directo si tienes update

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Compra finalizada correctamente"
        );

        redirectAttributes.addFlashAttribute(
                "tipo",
                "success"
        );

        return "redirect:/compras/" + id;
    }
}
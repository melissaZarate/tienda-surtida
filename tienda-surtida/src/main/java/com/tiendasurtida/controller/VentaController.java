
package com.tiendasurtida.controller;
import com.tiendasurtida.dto.ItemVentaDTO;
import com.tiendasurtida.dto.VentaDTO;
import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.service.VentaService;
import com.tiendasurtida.repository.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final ProductoRepository productoRepository;
    private final VentaService ventaService;

    public VentaController(ProductoRepository productoRepository, VentaService ventaService) {
        this.productoRepository = productoRepository;
        this.ventaService = ventaService;
    }
    //pantalla principal ára ventas
    @GetMapping
    public String vistaVenta(@RequestParam(required = false) String q,
                             Model model,
                             HttpSession session) {

        VentaDTO venta = (VentaDTO) session.getAttribute("venta");

        if (venta == null) {
            venta = new VentaDTO();
            session.setAttribute("venta", venta);
        }

        List<Producto> productos;

        if (q != null && !q.isEmpty()) {
            productos = productoRepository.buscarPorNombre(q);
        } else {
            productos = productoRepository.findAll();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("venta", venta);
        model.addAttribute("q", q);

        return "ventas/formulario";
    }
    //agegar producto de carrito
    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long idProducto, @RequestParam Integer cantidad, HttpSession session) {
          //cuandose agrega un producto, puede suceder que el cliente diga que ya no llevara el prodcuto, entonces enl dtbla
        //en la tabla detalleventa nodebe gurdarse todavia
        VentaDTO venta = (VentaDTO) session.getAttribute("venta");

        if (venta == null) {
            venta = new VentaDTO();
        }

        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ItemVentaDTO item = new ItemVentaDTO();
        item.setIdProducto(producto.getIdProducto());
        item.setNombreProducto(producto.getNombreProducto());
        item.setCantidad(cantidad);
        item.setPrecioUnitario(producto.getPrecioVentaProducto());
        item.setSubtotal(producto.getPrecioVentaProducto().multiply(java.math.BigDecimal.valueOf(cantidad))
        );

        venta.getItems().add(item);

        session.setAttribute("venta", venta);

        return "redirect:/ventas";
    }
    //eliminar prodcutode carrito
    @GetMapping("/eliminar/{index}")
    public String eliminarItem(@PathVariable int index, HttpSession session) {
        VentaDTO venta = (VentaDTO) session.getAttribute("venta");
        if (venta != null && index < venta.getItems().size()) {
            venta.getItems().remove(index);
        }
        session.setAttribute("venta", venta);
        return "redirect:/ventas";
    }
    //finalizar venta
    @PostMapping("/finalizar")
    public String finalizarVenta(HttpSession session, Principal principal) {

        VentaDTO venta = (VentaDTO) session.getAttribute("venta");

        if (venta == null || venta.getItems().isEmpty()) {
            return "redirect:/ventas?error=vacio";
        }

        try {
            ventaService.registrarVenta(venta, principal.getName());
            session.removeAttribute("venta");

        } catch (RuntimeException e) {
            return "redirect:/ventas?error=" + e.getMessage();
        }

        return "redirect:/ventas?success=1";
    }
    @GetMapping("/historial")
    public String historialVentas(Model model) {

        model.addAttribute(
                "ventas",
                ventaService.listarVentas() //obtiene todas laas ventas con fechas en ordendescendente
                //envia las ventas al thymeleaf ventas
        );

        return "ventas/historial";
    }
    @GetMapping("/detalle/{id}")
    public String detalleVenta(@PathVariable Long id,
                               Model model) {

        model.addAttribute("venta", ventaService.obtenerVentaPorId(id));

        return "ventas/detalle";
    }
  /*  @PostMapping("/finalizar")
    public String finalizarVenta(HttpSession session, Principal principal) {

        VentaDTO venta = (VentaDTO) session.getAttribute("venta");

        if (venta == null || venta.getItems().isEmpty()) {
            return "redirect:/ventas";
        }

        ventaService.registrarVenta(venta, principal.getName());

        session.removeAttribute("venta");

        return "redirect:/ventas";
    }*/
}
    //

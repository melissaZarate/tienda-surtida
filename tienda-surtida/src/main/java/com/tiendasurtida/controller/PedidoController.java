package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Pedido;
import com.tiendasurtida.service.PedidoService;
import com.tiendasurtida.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;


@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final CategoriaRepository categoriaRepository;

    public PedidoController(PedidoService pedidoService, CategoriaRepository categoriaRepository) {
        this.pedidoService = pedidoService;
        this.categoriaRepository = categoriaRepository;
    }

    //  LISTAR PEDIDOS
    @GetMapping
    public String listar(Model model) {
        List<Pedido> pedidos=pedidoService.listarPedidos();

        model.addAttribute("pedidos",pedidos );
        //verificar si existe un p4dido pendiente
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("existePendiente",pedidoService.existePedidoPendiente());

        return "pedido/lista";
    }

    //  VER DETALLE DEL PEDIDO
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model) {

        Pedido pedido = pedidoService.buscarPorId(id);

        if (pedido == null) {
            return "redirect:/pedidos";
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", pedido.getDetalles());

        return "pedido/ver";
    }

    //  GENERAR PEDIDO AUTOMÁTICO
    @PostMapping("/generar")
    public String generar(Authentication authentication, RedirectAttributes redirectAttributes) {

   //     Long idUsuario = 1L; // luego lo cambiamos por sesión real

        try {
            String username=authentication.getName();
            pedidoService.generarPedidoAutomaticoPorUsername(username);
            redirectAttributes.addFlashAttribute("success","Pedido Generado correctamente");
        } catch (Exception e) {
            System.out.println("Error generando pedido: " + e.getMessage());
        }

        return "redirect:/pedidos";
    }
    @PostMapping("/aprobar/{id}")
    public String aprobarPedido(@PathVariable Long id) {

        pedidoService.aprobarPedido(id);

        return "redirect:/pedidos/ver/" + id;
    }
    @PostMapping("/rechazar/{id}")
    public String rechazarPedido(@PathVariable Long id) {

        pedidoService.rechazarPedido(id);

        return "redirect:/pedidos/ver/" + id;
    }
    @PostMapping("/generar/categoria")
    public String generarPedidoPorCategoria(
            @RequestParam("idCategoria") Long idCategoria,
            RedirectAttributes ra, Authentication authentication) {

        String username=authentication.getName();

        try {
            pedidoService.generarPedidoPorCategoria(username, idCategoria);
            ra.addFlashAttribute("success", "Pedido generado correctamente");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/pedidos";
    }
    @GetMapping("/exportar/{id}")
    @ResponseBody
    public String exportarPedido(@PathVariable Long id) {
        return pedidoService.generarTextoPedido(id);
    }

}
/*package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Pedido;
import com.tiendasurtida.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    // LISTAR PEDIDOS

    @GetMapping
    public String listarPedidos(Model model) {

        List<Pedido> pedidos = pedidoService.listarPedidos();

        model.addAttribute("pedidos", pedidos);

        return "pedido/lista";
    }


    // VER DETALLE DEL PEDIDO

    @GetMapping("/ver/{id}")
    public String verPedido(@PathVariable Long id, Model model) {

        Pedido pedido = pedidoService.buscarPorId(id);

        if (pedido == null) {
            return "redirect:/pedidos";
        }

        model.addAttribute("pedido", pedido);

        return "pedido/ver";
    }
    // GENERAR PEDIDO AUTOMÁTICO

    @GetMapping("/generar")
    public String generarPedidoAutomatico() {

        // IMPORTANTE: aquí deberías obtener el usuario logueado
        Long idUsuario = 1L;

        pedidoService.generarPedidoAutomatico(idUsuario);

        return "redirect:/pedidos";
    }
}*/
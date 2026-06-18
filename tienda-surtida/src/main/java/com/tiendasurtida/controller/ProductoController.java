
package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.service.CategoriaService;
import com.tiendasurtida.service.ProductoService;
import com.tiendasurtida.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tiendasurtida.dto.DatosAjusteDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;

@Controller
@RequestMapping("/productos")// ruta principal localhost:8080/productos
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @GetMapping //metodo listar
    public String listar(Model model){

        model.addAttribute("productos", //se enviara a thymeleaf ${productos}
                productoService.listarProductos());

        model.addAttribute("categorias",
                categoriaService.listarCategorias()); //para combo

        model.addAttribute("unidades",
                unidadMedidaService.listarUnidades()); //para combo
        //aqui falta de socio uteracion 2
        model.addAttribute("totalProductos",productoService.listarProductos().size());

        model.addAttribute("producto", //th:object=${producto}
                new Producto());

        return "producto/lista";
    }
    @GetMapping("/buscar")//para buscar por nombre
    public String buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer categoria,
            Model model) {
        if(nombre!=null && !nombre.trim().isEmpty()){
            model.addAttribute("productos", productoService.buscarPorNombre(nombre));
        }
        else if(categoria!=null){
            model.addAttribute("productos", productoService.buscarPorCategoria(categoria));
        } else{
            model.addAttribute("productos",productoService.listarProductos());
        }

     //ARREGLAAAR PROBELMAAS      model.addAttribute("productos", productoService.buscar(nombre, categoria));

        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("unidades", unidadMedidaService.listarUnidades());

        model.addAttribute("producto", new Producto());
        model.addAttribute("nombre",nombre);
        model.addAttribute("categoriaSeleccionada",categoria);
        model.addAttribute("totalProductos",productoService.listarProductos().size()); //esto para as tarjetitas




        return "producto/lista";
    }
    @PostMapping("/guardar")
    public String guardar( Producto producto,
            @RequestParam String nombreProducto,
            @RequestParam String descripcionProducto,
            @RequestParam Integer stockMinimoProducto,
            @RequestParam(required = false) Boolean controlVencimientoProducto,
            @RequestParam(required = false) Boolean estadoProducto,
            @RequestParam Integer idCategoria,
            @RequestParam Integer idUnidad,
            RedirectAttributes redirectAttributes
    ) {

        try {

           // Producto producto = new Producto();

            producto.setNombreProducto(nombreProducto);
            producto.setDescripcionProducto(descripcionProducto);
            producto.setStockMinimoProducto(stockMinimoProducto);

            producto.setControlVencimientoProducto(controlVencimientoProducto != null);
            producto.setEstadoProducto(estadoProducto != null);

            producto.setCategoria(categoriaService.buscarPorId(idCategoria));
            producto.setUnidadMedida(unidadMedidaService.buscarPorId(idUnidad));

            productoService.guardarProducto(producto);

            redirectAttributes.addFlashAttribute("mensaje", "Producto guardado correctamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el producto");
            redirectAttributes.addFlashAttribute("tipo", "error");
        }

        return "redirect:/productos";
    }
    @PostMapping("/ajustar-precio")
    public String ajustarPrecio(
            @RequestParam Long idProducto,
            @RequestParam BigDecimal nuevoPorcentajeGanancia,
            RedirectAttributes redirectAttributes) {

        try {

            productoService.ajustarPrecioManual(
                    idProducto,
                    nuevoPorcentajeGanancia
            );

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Precio ajustado correctamente");

            redirectAttributes.addFlashAttribute(
                    "tipo",
                    "success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    e.getMessage());

            redirectAttributes.addFlashAttribute(
                    "tipo",
                    "error");
        }

        return "redirect:/productos";
    }
    @GetMapping("/datos-ajuste/{id}")
    @ResponseBody
    public DatosAjusteDTO obtenerDatosAjuste(
            @PathVariable Long id) {

        return productoService.obtenerDatosAjuste(id);
    }

   /* @GetMapping("/productos/datos-ajuste/{id}")
    @ResponseBody
    public DatosAjusteDTO obtenerDatos(@PathVariable Long id)*/
}
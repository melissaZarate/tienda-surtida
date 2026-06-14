
package com.tiendasurtida.controller;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.service.CategoriaService;
import com.tiendasurtida.service.ProductoService;
import com.tiendasurtida.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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




        return "producto/lista";
    }
}
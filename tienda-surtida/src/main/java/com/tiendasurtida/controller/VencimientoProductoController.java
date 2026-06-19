
package com.tiendasurtida.controller;

import com.tiendasurtida.dto.VencimientoDTO;
import com.tiendasurtida.entity.VencimientoProducto;
import com.tiendasurtida.service.VencimientoProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/vencimientos")
public class VencimientoProductoController {

    private final VencimientoProductoService vencimientoProductoService;

    public VencimientoProductoController(VencimientoProductoService vencimientoProductoService) {
        this.vencimientoProductoService = vencimientoProductoService;
    }
    //pagina para lista de lotes proximos a vencer
    @GetMapping("/lista")
    public String lista(Model model) {

        model.addAttribute(
                "lotes",
                vencimientoProductoService.listarProximosAVencer(365) // 1 año
        );

        return "vencimientos/lista";
    }

    @GetMapping("/proximos")
    @ResponseBody//para comunicarse con java sripc api json lenguaje universal
    public List<VencimientoDTO> proximosAVencer() {
        return vencimientoProductoService.listarProximosAVencer(30);
    }
}

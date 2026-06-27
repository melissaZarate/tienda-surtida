/*package com.tiendasurtida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String inicio() {

        return "dashboard/index";
    }
   /* public String inicio(Model model) {
        model.addAttribute("contenido","dashboard/index :: contenido");
        return "layout/base";
    }

}*/
package com.tiendasurtida.controller;

import com.tiendasurtida.dto.RecomendacionDTO;
import com.tiendasurtida.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("dashboard", dashboardService.obtenerDashboard());
        model.addAttribute("comparativo", dashboardService.obtenerComparativoVentas());
        List<RecomendacionDTO> recomendaciones = dashboardService.obtenerRecomendaciones();
        model.addAttribute("recomendaciones", recomendaciones);

        return "dashboard/index";
    }
}
package com.tiendasurtida.service;

import com.tiendasurtida.dto.DashboardComparativoDTO;
import com.tiendasurtida.dto.DashboardDTO;
import com.tiendasurtida.dto.RecomendacionDTO;

import java.util.List;

public interface DashboardService {
    DashboardDTO obtenerDashboard();
    DashboardComparativoDTO obtenerComparativoVentas();
    //AQUI MOSTRAR RECOMENDACIONES
    List<RecomendacionDTO> obtenerRecomendaciones();
}

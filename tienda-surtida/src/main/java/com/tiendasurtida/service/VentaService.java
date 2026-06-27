
package com.tiendasurtida.service;

import com.tiendasurtida.dto.ClienteDTO;
import com.tiendasurtida.dto.ReporteVentasDiariasDTO;
import com.tiendasurtida.dto.ReporteVentasResumenDTO;
import com.tiendasurtida.dto.VentaDTO;
import com.tiendasurtida.entity.Venta;
import com.tiendasurtida.repository.VentaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaService {

    Venta registrarVenta(VentaDTO ventaDTO, String username);
    List<Venta> listarVentas();
    Venta obtenerVentaPorId(Long id);
  //  Venta venta=VentaRepository
    //agregamos metodo para reporte de ventas
  //<ReporteVentasDiariasDTO> obtenerReporteVentasDiarias(LocalDateTime inicio, LocalDateTime fin);
   // ReporteVentasDiariasDTO obtenerReporteVentasDiarias(LocalDateTime inicio, LocalDateTime fin);
    ReporteVentasResumenDTO obtenerReporteVentasDiarias(LocalDateTime inicio, LocalDateTime fin);

}
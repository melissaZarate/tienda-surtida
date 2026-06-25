package com.tiendasurtida.service;


import com.tiendasurtida.dto.ProductoMasVendidoDTO;
import com.tiendasurtida.dto.ReporteFinancieroDTO;
import com.tiendasurtida.entity.Producto;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {

    ReporteFinancieroDTO reporteFinanciero(LocalDate inicio, LocalDate fin);

    List<Producto> reporteStockBajo();
    //reporte para prducto mas vendido
    List<ProductoMasVendidoDTO> obtenerProductosMasVendidos(LocalDate inicio, LocalDate fin);

}
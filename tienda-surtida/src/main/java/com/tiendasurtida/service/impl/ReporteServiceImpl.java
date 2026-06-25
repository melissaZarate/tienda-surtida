package com.tiendasurtida.service.impl;


import com.tiendasurtida.dto.ProductoMasVendidoDTO;
import com.tiendasurtida.dto.ReporteFinancieroDTO;
import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.repository.CompraRepository;
import com.tiendasurtida.repository.DetalleVentaRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.repository.VentaRepository;
import com.tiendasurtida.service.ReporteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public ReporteServiceImpl(VentaRepository ventaRepository, CompraRepository compraRepository, ProductoRepository productoRepository, DetalleVentaRepository detalleVentaRepository) {
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public ReporteFinancieroDTO reporteFinanciero(LocalDate inicio, LocalDate fin) {

        LocalDateTime ini = inicio.atStartOfDay();
        LocalDateTime finDt = fin.atTime(23, 59, 59);

        BigDecimal ventas = ventaRepository.sumarVentas(ini, finDt);
        BigDecimal compras = compraRepository.sumarCompras(ini, finDt);

        BigDecimal ganancias = ventas.subtract(compras);

        ReporteFinancieroDTO dto = new ReporteFinancieroDTO();
     //   dto.setFechaFin(fin);
       // dto.setFechaInicio(inicio);
        dto.setTotalVentas(ventas);
        dto.setTotalCompras(compras);
        dto.setGanancias(ganancias);

        return dto;
    }

    @Override
    public List<Producto> reporteStockBajo() {

        return productoRepository.productosStockBajo();

    }
    @Override
    public List<ProductoMasVendidoDTO> obtenerProductosMasVendidos(LocalDate inicio, LocalDate fin) {

        LocalDateTime fechaInicio = inicio.atStartOfDay(); //desde la hora 0
        LocalDateTime fechaFin = fin.atTime(23, 59, 59); //hasta el uktimosegundo de la fecha
        List<Object[]> resultados = detalleVentaRepository.obtenerProductosMasVendidos(fechaInicio, fechaFin);

        List<ProductoMasVendidoDTO> productos = new ArrayList<>();

        for (Object[] fila : resultados) {

            ProductoMasVendidoDTO dto = new ProductoMasVendidoDTO();
            dto.setNombreProducto((String) fila[0]);

            dto.setCantidadVendida(((Number) fila[1]).longValue());
            productos.add(dto);
        }

        return productos;
    }
}

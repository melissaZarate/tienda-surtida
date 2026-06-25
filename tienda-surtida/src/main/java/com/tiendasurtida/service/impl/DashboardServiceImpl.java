package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.DashboardDTO;
import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.repository.CajaRepository;
import com.tiendasurtida.repository.CompraRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.repository.VentaRepository;
import com.tiendasurtida.service.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final CajaRepository cajaRepository;
    //consructores


    public DashboardServiceImpl(ProductoRepository productoRepository, VentaRepository ventaRepository, CompraRepository compraRepository, CajaRepository cajaRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
        this.cajaRepository = cajaRepository;
    }

    @Override
    public DashboardDTO obtenerDashboard() {
        //objeto que enviaremos a la vista
        DashboardDTO dto = new DashboardDTO();
        LocalDate hoy = LocalDate.now(); //fecha actual enviamos
        LocalDateTime inicio = hoy.atStartOfDay();
        LocalDateTime fin = hoy.atTime(23, 59, 59);
        dto.setTotalProductos(productoRepository.contarProductosActivos()); //contar total de prodyuctos
        dto.setProductosStockBajo(productoRepository.contarProductosStockBajo());//stock bajo
        BigDecimal ventasHoy = ventaRepository.obtenerVentasEntreFechas(inicio, fin);
//
        dto.setVentasHoy(ventasHoy); //venas actuales hoy

        Double comprasHoy = compraRepository.obtenerComprasEntreFechas(inicio, fin); //compras actuales
        dto.setComprasHoy(BigDecimal.valueOf(comprasHoy));
        BigDecimal gananciasHoy = ventasHoy.subtract(BigDecimal.valueOf(comprasHoy));
        dto.setGananciasHoy(gananciasHoy);
        //para caja
        Caja caja = cajaRepository.findByEstadoIgnoreCase("ABIERTA").orElse(null);
        //siexiste caja
        if (caja != null) {
            dto.setCajaActual(caja.getSaldoFinal());
        } else {
            dto.setCajaActual(BigDecimal.ZERO);
        }
        return dto;

    }

}
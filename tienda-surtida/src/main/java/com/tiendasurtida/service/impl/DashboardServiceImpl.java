package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.DashboardComparativoDTO;
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
    @Override
    public DashboardComparativoDTO obtenerComparativoVentas() {

        DashboardComparativoDTO dto = new DashboardComparativoDTO();

        LocalDate hoy = LocalDate.now();
        LocalDate ayer = hoy.minusDays(1);

        LocalDateTime inicioHoy = hoy.atStartOfDay();
        LocalDateTime finHoy = hoy.atTime(23, 59, 59);

        LocalDateTime inicioAyer = ayer.atStartOfDay();
        LocalDateTime finAyer = ayer.atTime(23, 59, 59);

        BigDecimal ventasHoy = ventaRepository.obtenerTotalEntreFechas(inicioHoy, finHoy);
        BigDecimal ventasAyer = ventaRepository.obtenerTotalEntreFechas(inicioAyer, finAyer);
        String estado;
        if(ventasHoy.compareTo(ventasAyer)>0){
            estado="CRECIMIENTO";
        }
        else if(ventasHoy.compareTo(ventasAyer)<0){
            estado="caida";
        }
        else{
            estado="ESTABLE";
        }

        dto.setEstado(estado);
        dto.setVentasHoy(ventasHoy);
        dto.setVentasAyer(ventasAyer);
        //ccalcuklo
        BigDecimal diferencia = ventasHoy.subtract(ventasAyer); //resta positivi: crecimienti negativo caida
        dto.setDiferencia(diferencia);

        BigDecimal porcentaje = BigDecimal.ZERO;

        if (ventasAyer.compareTo(BigDecimal.ZERO) > 0) { //porcentaje=diferencia*100)/ventassayer solosiayer no es ceo
            porcentaje = diferencia.multiply(BigDecimal.valueOf(100)).divide(ventasAyer, 2, java.math.RoundingMode.HALF_UP);
        }

        dto.setPorcentajeCrecimiento(porcentaje);

        return dto;
    }

}
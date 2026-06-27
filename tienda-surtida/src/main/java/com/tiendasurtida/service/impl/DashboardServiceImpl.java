package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.DashboardComparativoDTO;
import com.tiendasurtida.dto.DashboardDTO;
import com.tiendasurtida.dto.RecomendacionDTO;
import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.repository.*;
import com.tiendasurtida.service.DashboardService;
import com.tiendasurtida.util.FechaUtil;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final CajaRepository cajaRepository;
    private final PedidoRepository pedidoRepository;
    //consructores


    public DashboardServiceImpl(ProductoRepository productoRepository, VentaRepository ventaRepository, CompraRepository compraRepository, CajaRepository cajaRepository, PedidoRepository pedidoRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
        this.cajaRepository = cajaRepository;
        this.pedidoRepository = pedidoRepository;
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

    @Override
    public List<RecomendacionDTO> obtenerRecomendaciones() { //en list porque ouede ocurrir que un dia ocurra mas recomendaciines
        List<RecomendacionDTO> recomendaciones = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        if (FechaUtil.esDiaAbastecimiento(hoy)) {
            recomendaciones.add(
                    new RecomendacionDTO(
                            FechaUtil.mensajeAbastecimiento(hoy),
                            "INFO"
                    )
            );
        }

       // String dia= FechaUtil.obtenerDiaEnEspanol(LocalDate.now());

     //   LocalDate hoy = LocalDate.now();
        //primara regla de abstaecimientos;dia de abastecimiento

        //segunda regla de abstecimiento: productos con stok bajo
        productoRepository.contarProductosStockBajo();
        long stockBajo = productoRepository.contarProductosStockBajo(); //aqui usamos la consulta almacenads
        if(stockBajo>0){

            recomendaciones.add(new RecomendacionDTO("Existen " + stockBajo + " productos con stock bajo. Revise el reporte de inventario.", "WARNING"));
        }else{
            recomendaciones.add(new RecomendacionDTO("El inventario se encuentra dentro de los niveles establecidos.", "SUCCESS"));
        }
        //recera regla pedidos pendientes
       // long pedidosPendientes= pedidoRepository.countByEstadoPedido_NombreEstado("PENDIENTE");
        // Tercera regla: pedidos pendientes
        long pedidosPendientes = pedidoRepository.countByEstadoPedido_NombreEstadoPedido("PENDIENTE"); //lo que devuelve la cpnsulta

        if (pedidosPendientes > 0) {

            recomendaciones.add(new RecomendacionDTO("Hay " + pedidosPendientes + " pedidos pendientes de aprobación.", "WARNING"));

        } else {

            recomendaciones.add(new RecomendacionDTO("No existen pedidos pendientes de aprobación.", "SUCCESS"));
        }

        return recomendaciones;


    }
}
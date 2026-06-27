package com.tiendasurtida.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReporteVentasResumenDTO {

    private List<ReporteVentasDiariasDTO> detalleVentas;

    private BigDecimal totalPeriodo;
    private Long cantidadVentasPeriodo;

    public ReporteVentasResumenDTO() {
    }

    public ReporteVentasResumenDTO(List<ReporteVentasDiariasDTO> detalleVentas, BigDecimal totalPeriodo, Long cantidadVentasPeriodo) {
        this.detalleVentas = detalleVentas;
        this.totalPeriodo = totalPeriodo;
        this.cantidadVentasPeriodo = cantidadVentasPeriodo;
    }

    public List<ReporteVentasDiariasDTO> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<ReporteVentasDiariasDTO> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public BigDecimal getTotalPeriodo() {
        return totalPeriodo;
    }

    public void setTotalPeriodo(BigDecimal totalPeriodo) {
        this.totalPeriodo = totalPeriodo;
    }

    public Long getCantidadVentasPeriodo() {
        return cantidadVentasPeriodo;
    }

    public void setCantidadVentasPeriodo(Long cantidadVentasPeriodo) {
        this.cantidadVentasPeriodo = cantidadVentasPeriodo;
    }
}

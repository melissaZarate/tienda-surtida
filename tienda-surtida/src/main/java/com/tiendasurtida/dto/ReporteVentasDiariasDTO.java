package com.tiendasurtida.dto; //parconsuktas

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReporteVentasDiariasDTO {
    private LocalDate fecha;
    private Long cantidadVentas;
    private BigDecimal totalVendido;
    //constructores

    public ReporteVentasDiariasDTO() {
    }

    public ReporteVentasDiariasDTO(LocalDate fecha, Long cantidadVentas, BigDecimal totalVendido) {
        this.fecha = fecha;
        this.cantidadVentas = cantidadVentas;
        this.totalVendido = totalVendido;
    }
    //getters ysetter

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public BigDecimal getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(BigDecimal totalVendido) {
        this.totalVendido = totalVendido;
    }
}

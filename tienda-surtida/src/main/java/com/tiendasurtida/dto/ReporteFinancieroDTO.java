package com.tiendasurtida.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class ReporteFinancieroDTO {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private BigDecimal totalVentas;
    private BigDecimal totalCompras;
    private BigDecimal ganancias;

    private Long cantidadVentas;
    private Long cantidadCompras;

    // constructor


    public ReporteFinancieroDTO() {
    }

    public ReporteFinancieroDTO(LocalDate fechaInicio, LocalDate fechaFin, BigDecimal totalVentas, BigDecimal totalCompras, BigDecimal ganancias, Long cantidadVentas, Long cantidadCompras) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalVentas = totalVentas;
        this.totalCompras = totalCompras;
        this.ganancias = ganancias;
        this.cantidadVentas = cantidadVentas;
        this.cantidadCompras = cantidadCompras;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(BigDecimal totalCompras) {
        this.totalCompras = totalCompras;
    }

    public BigDecimal getGanancias() {
        return ganancias;
    }

    public void setGanancias(BigDecimal ganancias) {
        this.ganancias = ganancias;
    }

    public Long getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public Long getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(Long cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }
    // + getters + setters
}
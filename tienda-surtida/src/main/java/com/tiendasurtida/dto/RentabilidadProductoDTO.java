package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class RentabilidadProductoDTO {
    //para mstrar en reporte utilizamos datos de diferentes tablas
    private String producto;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Long cantidadVendida;
    private BigDecimal ingresoGenerado;
    private BigDecimal ganancia;
    //onstructores

    public RentabilidadProductoDTO() {
    }

    public RentabilidadProductoDTO(String producto, BigDecimal precioCompra, BigDecimal precioVenta, Long cantidadVendida, BigDecimal ingresoGenerado, BigDecimal ganancia) {
        this.producto = producto;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.cantidadVendida = cantidadVendida;
        this.ingresoGenerado = ingresoGenerado;
        this.ganancia = ganancia;
    }
    //getter y setter

    public BigDecimal getGanancia() {
        return ganancia;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
    }

    public BigDecimal getIngresoGenerado() {
        return ingresoGenerado;
    }

    public void setIngresoGenerado(BigDecimal ingresoGenerado) {
        this.ingresoGenerado = ingresoGenerado;
    }

    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}

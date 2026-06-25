
package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class DashboardDTO {

    private Long totalProductos;
    private Long productosStockBajo;
    private BigDecimal ventasHoy;
    private BigDecimal comprasHoy;
    private BigDecimal gananciasHoy;
    private BigDecimal cajaActual;
    private String productoMasVendido;
//constructor
    public DashboardDTO() {
    }

    public DashboardDTO(Long totalProductos, Long productosStockBajo, BigDecimal ventasHoy, BigDecimal comprasHoy, BigDecimal gananciasHoy, BigDecimal cajaActual, String productoMasVendido) {
        this.totalProductos = totalProductos;
        this.productosStockBajo = productosStockBajo;
        this.ventasHoy = ventasHoy;
        this.comprasHoy = comprasHoy;
        this.gananciasHoy = gananciasHoy;
        this.cajaActual = cajaActual;
        this.productoMasVendido = productoMasVendido;
    }

    public Long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Long totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Long getProductosStockBajo() {
        return productosStockBajo;
    }

    public void setProductosStockBajo(Long productosStockBajo) {
        this.productosStockBajo = productosStockBajo;
    }

    public BigDecimal getVentasHoy() {
        return ventasHoy;
    }

    public void setVentasHoy(BigDecimal ventasHoy) {
        this.ventasHoy = ventasHoy;
    }

    public BigDecimal getComprasHoy() {
        return comprasHoy;
    }

    public void setComprasHoy(BigDecimal comprasHoy) {
        this.comprasHoy = comprasHoy;
    }

    public BigDecimal getGananciasHoy() {
        return gananciasHoy;
    }

    public void setGananciasHoy(BigDecimal gananciasHoy) {
        this.gananciasHoy = gananciasHoy;
    }

    public BigDecimal getCajaActual() {
        return cajaActual;
    }

    public void setCajaActual(BigDecimal cajaActual) {
        this.cajaActual = cajaActual;
    }

    public String getProductoMasVendido() {
        return productoMasVendido;
    }

    public void setProductoMasVendido(String productoMasVendido) {
        this.productoMasVendido = productoMasVendido;
    }
}

package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class DatosAjusteDTO {

    private BigDecimal costoCompra;
    private BigDecimal porcentajeGanancia;
    private BigDecimal precioVenta;

    public DatosAjusteDTO() {
    }

    public DatosAjusteDTO(
            BigDecimal costoCompra,
            BigDecimal porcentajeGanancia,
            BigDecimal precioVenta) {

        this.costoCompra = costoCompra;
        this.porcentajeGanancia = porcentajeGanancia;
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(BigDecimal costoCompra) {
        this.costoCompra = costoCompra;
    }

    public BigDecimal getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(BigDecimal porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}
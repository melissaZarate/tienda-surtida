
package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class CajaResumenDTO {

    private BigDecimal acumuladoAnterior;
    private BigDecimal ventasHoy;
    private BigDecimal comprasHoy;
    private BigDecimal saldoPreliminar;

    public CajaResumenDTO() {
    }

    public CajaResumenDTO(BigDecimal acumuladoAnterior, BigDecimal ventasHoy, BigDecimal comprasHoy, BigDecimal saldoPreliminar) {
        this.acumuladoAnterior = acumuladoAnterior;
        this.ventasHoy = ventasHoy;
        this.comprasHoy = comprasHoy;
        this.saldoPreliminar = saldoPreliminar;
    }

    public BigDecimal getAcumuladoAnterior() {
        return acumuladoAnterior;
    }

    public void setAcumuladoAnterior(BigDecimal acumuladoAnterior) {
        this.acumuladoAnterior = acumuladoAnterior;
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

    public BigDecimal getSaldoPreliminar() {
        return saldoPreliminar;
    }

    public void setSaldoPreliminar(BigDecimal saldoPreliminar) {
        this.saldoPreliminar = saldoPreliminar;
    }
}

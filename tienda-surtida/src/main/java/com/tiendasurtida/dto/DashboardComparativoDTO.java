
package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class DashboardComparativoDTO {

    private BigDecimal ventasHoy;
    private BigDecimal ventasAyer;
    private BigDecimal diferencia;
    private BigDecimal porcentajeCrecimiento;
    private String estado;

    public DashboardComparativoDTO() {
    }

    public DashboardComparativoDTO(BigDecimal ventasHoy, BigDecimal ventasAyer, BigDecimal diferencia, BigDecimal porcentajeCrecimiento, String estado) {
        this.ventasHoy = ventasHoy;
        this.ventasAyer = ventasAyer;
        this.diferencia = diferencia;
        this.porcentajeCrecimiento = porcentajeCrecimiento;
        this.estado = estado;
    }

    // getters y setters

    public BigDecimal getVentasHoy() {
        return ventasHoy;
    }

    public void setVentasHoy(BigDecimal ventasHoy) {
        this.ventasHoy = ventasHoy;
    }

    public BigDecimal getVentasAyer() {
        return ventasAyer;
    }

    public void setVentasAyer(BigDecimal ventasAyer) {
        this.ventasAyer = ventasAyer;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {

        this.diferencia = diferencia;
    }

    public BigDecimal getPorcentajeCrecimiento() {

        return porcentajeCrecimiento;
    }

    public void setPorcentajeCrecimiento(BigDecimal porcentajeCrecimiento) {
        this.porcentajeCrecimiento = porcentajeCrecimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
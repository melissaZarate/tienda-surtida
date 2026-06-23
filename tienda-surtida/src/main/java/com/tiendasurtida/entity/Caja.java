
package com.tiendasurtida.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "caja")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caja")
    private Long idCaja;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "monto_inicial", nullable = false)
    private BigDecimal montoInicial;

    @Column(name = "total_ingresos")
    private BigDecimal totalIngresos;

    @Column(name = "total_egresos")
    private BigDecimal totalEgresos;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Column(name = "estado", nullable = false)
    private String estado;

    @OneToMany(mappedBy = "caja")
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "caja")
    private List<Compra> compras = new ArrayList<>();

    public Caja() {
    }

    public Caja(Long idCaja, LocalDateTime fechaApertura, LocalDateTime fechaCierre, BigDecimal montoInicial, BigDecimal totalIngresos, BigDecimal totalEgresos, BigDecimal saldoFinal, String estado, List<Venta> ventas, List<Compra> compras) {
        this.idCaja = idCaja;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.montoInicial = montoInicial;
        this.totalIngresos = totalIngresos;
        this.totalEgresos = totalEgresos;
        this.saldoFinal = saldoFinal;
        this.estado = estado;
        this.ventas = ventas;
        this.compras = compras;
    }

    // GETTERS Y SETTERS

    public Long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public BigDecimal getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(BigDecimal totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}

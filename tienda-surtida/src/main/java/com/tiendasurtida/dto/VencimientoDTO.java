
package com.tiendasurtida.dto;

import java.time.LocalDate;

public class VencimientoDTO {

    private String nombreProducto;
    private Integer cantidad;
    private LocalDate fechaVencimiento;
    private long diasRestantes;

    // constructor
    public VencimientoDTO(String nombreProducto, Integer cantidad, LocalDate fechaVencimiento, long diasRestantes) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.diasRestantes = diasRestantes;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public long getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
    // getters y setters
}
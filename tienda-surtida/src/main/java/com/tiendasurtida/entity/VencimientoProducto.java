
package com.tiendasurtida.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vencimiento_producto")
public class VencimientoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vencimiento")
    private Long idVencimiento;

    @Column(name = "cantidad_vencimiento", nullable = false)
    private Integer cantidadVencimiento;

    @Column(name = "fecha_vencimiento_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    //  RELACIÓN CON PRODUCTO (MUCHOS LOTES -> 1 PRODUCTO)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    // Constructor vacío (JPA)
    public VencimientoProducto() {
    }

    // Constructor útil (opcional)
    public VencimientoProducto(Integer cantidadVencimiento, LocalDate fechaVencimiento, Producto producto) {
        this.cantidadVencimiento = cantidadVencimiento;
        this.fechaVencimiento = fechaVencimiento;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getIdVencimiento() {
        return idVencimiento;
    }

    public void setIdVencimiento(Long idVencimiento) {
        this.idVencimiento = idVencimiento;
    }

    public Integer getCantidadVencimiento() {
        return cantidadVencimiento;
    }

    public void setCantidadVencimiento(Integer cantidadVencimiento) {
        this.cantidadVencimiento = cantidadVencimiento;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
package com.tiendasurtida.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "historial_precio")
public class HistorialPrecio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long idHistorial;

    @Column(name = "precio_compra_historial")
    private BigDecimal precioCompraHistorial;

    @Column(name = "precio_venta_historial")
    private BigDecimal precioVentaHistorial;

    @Column(name = "fecha_cambio_historial")
    private LocalDateTime fechaCambioHistorial;

    @Column(name = "motivo_historial")
    private String motivoHistorial;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public HistorialPrecio() {
    }

    public HistorialPrecio(Long idHistorial, BigDecimal precioCompraHistorial, BigDecimal precioVentaHistorial, LocalDateTime fechaCambioHistorial, String motivoHistorial, Producto producto) {
        this.idHistorial = idHistorial;
        this.precioCompraHistorial = precioCompraHistorial;
        this.precioVentaHistorial = precioVentaHistorial;
        this.fechaCambioHistorial = fechaCambioHistorial;
        this.motivoHistorial = motivoHistorial;
        this.producto = producto;
    }
// getters y setters

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public BigDecimal getPrecioCompraHistorial() {
        return precioCompraHistorial;
    }

    public void setPrecioCompraHistorial(BigDecimal precioCompraHistorial) {
        this.precioCompraHistorial = precioCompraHistorial;
    }

    public BigDecimal getPrecioVentaHistorial() {
        return precioVentaHistorial;
    }

    public void setPrecioVentaHistorial(BigDecimal precioVentaHistorial) {
        this.precioVentaHistorial = precioVentaHistorial;
    }

    public LocalDateTime getFechaCambioHistorial() {
        return fechaCambioHistorial;
    }

    public void setFechaCambioHistorial(LocalDateTime fechaCambioHistorial) {
        this.fechaCambioHistorial = fechaCambioHistorial;
    }

    public String getMotivoHistorial() {
        return motivoHistorial;
    }

    public void setMotivoHistorial(String motivoHistorial) {
        this.motivoHistorial = motivoHistorial;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
package com.tiendasurtida.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "cantidad_detalle")
    private Integer cantidadDetalle;

    @Column(name = "precio_detalle_venta")
    private BigDecimal precioDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    //constructores

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetalle, Integer cantidadDetalle, BigDecimal precioDetalleVenta, Venta venta, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidadDetalle = cantidadDetalle;
        this.precioDetalleVenta = precioDetalleVenta;
        this.venta = venta;
        this.producto = producto;
    }
    // GETTERS Y SETTERS

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(Integer cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public BigDecimal getPrecioDetalleVenta() {
        return precioDetalleVenta;
    }

    public void setPrecioDetalleVenta(BigDecimal precioDetalleVenta) {
        this.precioDetalleVenta = precioDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
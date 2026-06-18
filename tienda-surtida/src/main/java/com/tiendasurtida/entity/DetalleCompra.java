
package com.tiendasurtida.entity;


import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "cantidad_detalle")
    private Integer cantidadDetalle;

    @Column(name = "precio_compra_detalle")
    private BigDecimal precioCompraDetalle;

    @Column(name = "precio_total_detalle")
    private BigDecimal precioTotalDetalle;

    @Column(name = "porcentaje_ganancia_detalle")
    private BigDecimal porcentajeGananciaDetalle;

    // Relacioncon comroaC
    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    // Relacion con producto
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    // CONSTRUCTORES
    public DetalleCompra() {
    }

    public DetalleCompra(Producto producto, Compra compra, BigDecimal porcentajeGananciaDetalle, BigDecimal precioTotalDetalle, BigDecimal precioCompraDetalle, Integer cantidadDetalle, Long idDetalle) {
        this.producto = producto;
        this.compra = compra;
        this.porcentajeGananciaDetalle = porcentajeGananciaDetalle;
        this.precioTotalDetalle = precioTotalDetalle;
        this.precioCompraDetalle = precioCompraDetalle;
        this.cantidadDetalle = cantidadDetalle;
        this.idDetalle = idDetalle;
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

    public BigDecimal getPrecioCompraDetalle() {
        return precioCompraDetalle;
    }

    public void setPrecioCompraDetalle(BigDecimal precioCompraDetalle) {
        this.precioCompraDetalle = precioCompraDetalle;
    }

    public BigDecimal getPorcentajeGananciaDetalle() {
        return porcentajeGananciaDetalle;
    }

    public void setPorcentajeGananciaDetalle(BigDecimal porcentajeGananciaDetalle) {
        this.porcentajeGananciaDetalle = porcentajeGananciaDetalle;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecioTotalDetalle() {
        return precioTotalDetalle;
    }

    public void setPrecioTotalDetalle(BigDecimal precioTotalDetalle) {
        this.precioTotalDetalle = precioTotalDetalle;
    }
}
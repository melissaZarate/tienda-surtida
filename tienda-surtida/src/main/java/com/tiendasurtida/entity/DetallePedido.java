package com.tiendasurtida.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    private Integer cantidadDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;


    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name="id_unidad_compra")
    private UnidadCompra unidadCompra;

    @Column(name= "precio_total_sugerido")
    private BigDecimal precioTotalSugerido;
    //constructor


    public DetallePedido() {
    }

    public DetallePedido(Long idDetalle, Integer cantidadDetalle, Pedido pedido, Producto producto, UnidadCompra unidadCompra, BigDecimal precioTotalSugerido) {
        this.idDetalle = idDetalle;
        this.cantidadDetalle = cantidadDetalle;
        this.pedido = pedido;
        this.producto = producto;
        this.unidadCompra = unidadCompra;
        this.precioTotalSugerido = precioTotalSugerido;
    }

    // getters y setters

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UnidadCompra getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(UnidadCompra unidadCompra) {
        this.unidadCompra = unidadCompra;
    }

    public BigDecimal getPrecioTotalSugerido() {
        return precioTotalSugerido;
    }

    public void setPrecioTotalSugerido(BigDecimal precioTotalSugerido) {
        this.precioTotalSugerido = precioTotalSugerido;
    }
}

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

    @Column(name = "cantidad_detalle") //la cantidad que ompro segun la unidad de comr
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

    //relacion con la unidad de producto comrpaod
    @ManyToOne
    @JoinColumn(name = "id_unidad_compra")
    private UnidadCompra unidadCompra;
    @Column(name = "unidades_ingresadas_detalle") //esto es para cuando el usyuario ungrese  en detalle la cantidad
    private Integer unidadesIngresadasDetalle;


    // CONSTRUCTORES
    public DetalleCompra() {
    }

    public DetalleCompra(Long idDetalle, Integer cantidadDetalle, BigDecimal precioCompraDetalle, BigDecimal precioTotalDetalle, BigDecimal porcentajeGananciaDetalle, Compra compra, Producto producto, UnidadCompra unidadCompra, Integer unidadesIngresadasDetalle) {
        this.idDetalle = idDetalle;
        this.cantidadDetalle = cantidadDetalle;
        this.precioCompraDetalle = precioCompraDetalle;
        this.precioTotalDetalle = precioTotalDetalle;
        this.porcentajeGananciaDetalle = porcentajeGananciaDetalle;
        this.compra = compra;
        this.producto = producto;
        this.unidadCompra = unidadCompra;
        this.unidadesIngresadasDetalle = unidadesIngresadasDetalle;
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

    public UnidadCompra getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(UnidadCompra unidadCompra) {
        this.unidadCompra = unidadCompra;
    }

    public Integer getUnidadesIngresadasDetalle() {
        return unidadesIngresadasDetalle;
    }

    public void setUnidadesIngresadasDetalle(Integer unidadesIngresadasDetalle) {
        this.unidadesIngresadasDetalle = unidadesIngresadasDetalle;
    }
}
package com.tiendasurtida.entity;
import com.tiendasurtida.entity.VencimientoProducto;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity //esta clase represent una tabla de la base de dato
@Table(name = "producto") //esta entidd corresponde a la tabla producto
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//es la llave promaria y  my sql genera el id automaticamnete
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @Column(name = "precio_venta_producto")
    private BigDecimal precioVentaProducto = BigDecimal.ZERO; //evitar null en precioventa

    @Column(name = "stock_actual_producto")
    private Integer stockActualProducto = 0; //esto actualizar hasa la teracion 4

    @Column(name = "stock_minimo_producto")
    private Integer stockMinimoProducto;

    @Column(name = "estado_producto")
    private Boolean estadoProducto;

    @Column(name = "control_vencimiento_producto")
    private Boolean controlVencimientoProducto;
    //aqui añadimos para realizar los pedidos
    @Column(name = "pedido_por_paquete")
    private Boolean pedidoPorPaquete = false;

    @Column(name = "cantidad_por_paquete")
    private Integer cantidadPorPaquete;

    //aqui los productos que tienen claves foranea
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_unidad")
    private UnidadMedida unidadMedida;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VencimientoProducto> vencimientos;

    public Producto() {
    }

    public Producto(Long idProducto, String nombreProducto, String descripcionProducto, BigDecimal precioVentaProducto, Integer stockActualProducto, Integer stockMinimoProducto, Boolean estadoProducto, Boolean controlVencimientoProducto, Categoria categoria, UnidadMedida unidadMedida, List<VencimientoProducto> vencimientos) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioVentaProducto = precioVentaProducto;
        this.stockActualProducto = stockActualProducto;
        this.stockMinimoProducto = stockMinimoProducto;
        this.estadoProducto = estadoProducto;
        this.controlVencimientoProducto = controlVencimientoProducto;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
        this.vencimientos = vencimientos;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getPrecioVentaProducto() {
        return precioVentaProducto;
    }

    public void setPrecioVentaProducto(BigDecimal precioVentaProducto) {
        this.precioVentaProducto = precioVentaProducto;
    }

    public Integer getStockActualProducto() {
        return stockActualProducto;
    }

    public void setStockActualProducto(Integer stockActualProducto) {
        this.stockActualProducto = stockActualProducto;
    }

    public Integer getStockMinimoProducto() {
        return stockMinimoProducto;
    }

    public void setStockMinimoProducto(Integer stockMinimoProducto) {
        this.stockMinimoProducto = stockMinimoProducto;
    }

    public Boolean getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(Boolean estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public Boolean getControlVencimientoProducto() {
        return controlVencimientoProducto;
    }

    public void setControlVencimientoProducto(Boolean controlVencimientoProducto) {
        this.controlVencimientoProducto = controlVencimientoProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public List<VencimientoProducto> getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(List<VencimientoProducto> vencimientos) {
        this.vencimientos = vencimientos;
    }
}
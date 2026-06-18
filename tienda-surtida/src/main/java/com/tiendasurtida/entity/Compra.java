package com.tiendasurtida.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(name = "total_compra")
    private Double totalCompra;

    @Column(name = "observacion_compra")
    private String observacionCompra;
    @Column(name = "estado_compra")
    private String estadoCompra;

    // relacion con proveedr
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // relacion usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // rdlacion condetalleProducto
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<DetalleCompra> detalles=new ArrayList<>();

    // CONSTRUCTORES
    public Compra() {
    }

    public Compra(Long idCompra, LocalDateTime fechaCompra, Double totalCompra, String observacionCompra, String estadoCompra, Proveedor proveedor, Usuario usuario, List<DetalleCompra> detalles) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.observacionCompra = observacionCompra;
        this.estadoCompra = estadoCompra;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.detalles = detalles;
    }

    // GETTERS Y SETTERS
    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getObservacionCompra() {
        return observacionCompra;
    }

    public void setObservacionCompra(String observacionCompra) {
        this.observacionCompra = observacionCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
}
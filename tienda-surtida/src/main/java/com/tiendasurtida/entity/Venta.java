
package com.tiendasurtida.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @Column(name = "total_venta")
    private BigDecimal totalVenta;
    @Column(name = "ruta_pdf")
    private String rutaPdf;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_caja")
    private Caja caja;

    @OneToMany(mappedBy = "venta",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
    //constructor

    public Venta() {
    }

    public Venta(Long idVenta, LocalDateTime fechaVenta, BigDecimal totalVenta, String rutaPdf, Usuario usuario, Cliente cliente, Caja caja, List<DetalleVenta> detalles) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.rutaPdf = rutaPdf;
        this.usuario = usuario;
        this.cliente = cliente;
        this.caja = caja;
        this.detalles = detalles;
    }

// GETTERS Y SETTERS

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }
}

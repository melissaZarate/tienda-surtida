
package com.tiendasurtida.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDateTime fechaGeneracionPedido;

    private String observacionPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_estado_pedido")
    private EstadoPedido estadoPedido; //se guarda un objeto

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles=new ArrayList<>();

    public Pedido() {
    }
//constructor

    public Pedido(Long idPedido, LocalDateTime fechaGeneracionPedido, String observacionPedido, Usuario usuario, EstadoPedido estadoPedido, List<DetallePedido> detalles) {
        this.idPedido = idPedido;
        this.fechaGeneracionPedido = fechaGeneracionPedido;
        this.observacionPedido = observacionPedido;
        this.usuario = usuario;
        this.estadoPedido = estadoPedido;
        this.detalles = detalles;
    }
    //getter y setter

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFechaGeneracionPedido() {
        return fechaGeneracionPedido;
    }

    public void setFechaGeneracionPedido(LocalDateTime fechaGeneracionPedido) {
        this.fechaGeneracionPedido = fechaGeneracionPedido;
    }

    public String getObservacionPedido() {
        return observacionPedido;
    }

    public void setObservacionPedido(String observacionPedido) {
        this.observacionPedido = observacionPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }


}
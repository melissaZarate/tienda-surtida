package com.tiendasurtida.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "ci_cliente", unique = true, nullable = false)
    private Integer ciCliente;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "apellido_cliente")
    private String apellidoCliente;

    @Column(name = "telefono_cliente")
    private String telefonoCliente;

    @Column(name = "direccion_cliente")
    private String direccionCliente;

    @Column(name = "estado_cliente")
    private Boolean estadoCliente = true;
    @OneToMany(mappedBy="cliente")
    private List<Venta> ventas= new ArrayList<>();

    public Cliente() {

    }

    public Cliente(Long idCliente, Integer ciCliente, String nombreCliente, String apellidoCliente, String telefonoCliente, String direccionCliente, Boolean estadoCliente) {
        this.idCliente = idCliente;
        this.ciCliente = ciCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.estadoCliente = estadoCliente;
    }
    // getters y setters

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getCiCliente() {
        return ciCliente;
    }

    public void setCiCliente(Integer ciCliente) {
        this.ciCliente = ciCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public Boolean getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(Boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
}
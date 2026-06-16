package com.tiendasurtida.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;

    @Column(name = "direccion_proveedor")
    private String direccionProveedor;

    @Column(name = "estado_proveedor")
    private Boolean estadoProveedor = true;

    // Constructores

    public Proveedor() {
    }

    public Proveedor(Long idProveedor, String nombreProveedor,
                     String telefonoProveedor,
                     String direccionProveedor,
                     Boolean estadoProveedor) {

        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    // Getters y Setters

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public Boolean getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(Boolean estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }
}
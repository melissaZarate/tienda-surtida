package com.tiendasurtida.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

    @Column(name = "estado_categoria")
    private Boolean estadoCategoria;

    // getters y setters


    public Categoria() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public Boolean getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(Boolean estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }
}
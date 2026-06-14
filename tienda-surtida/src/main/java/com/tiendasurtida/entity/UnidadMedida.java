package com.tiendasurtida.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "unidad_medida")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Integer idUnidad;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre_unidad")
    private String nombreUnidad;

    @NotBlank(message = "La abreviación es obligatoria")
    @Column(name = "abreviacion_unidad")
    private String abreviacionUnidad;

    public UnidadMedida() {
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(String abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
    }
}
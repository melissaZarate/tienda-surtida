
package com.tiendasurtida.entity;
import jakarta.persistence.*;
@Entity @Table(name = "unidad_compra")
public class UnidadCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_compra")
    private Long idUnidadCompra;
    @Column(name = "nombre_unidad_compra")
    private String nombreUnidadCompra;
    @Column(name = "equivalencia_unidad_compra")
    private Integer equivalenciaUnidadCompra;
    //constructires


    public UnidadCompra() {
    }

    public UnidadCompra(Long idUnidadCompra, String nombreUnidadCompra, Integer equivalenciaUnidadCompra) {
        this.idUnidadCompra = idUnidadCompra;
        this.nombreUnidadCompra = nombreUnidadCompra;
        this.equivalenciaUnidadCompra = equivalenciaUnidadCompra;
    }
    //getters ysetters


    public Long getIdUnidadCompra() {
        return idUnidadCompra;
    }

    public void setIdUnidadCompra(Long idUnidadCompra) {
        this.idUnidadCompra = idUnidadCompra;
    }

    public String getNombreUnidadCompra() {
        return nombreUnidadCompra;
    }

    public void setNombreUnidadCompra(String nombreUnidadCompra) {
        this.nombreUnidadCompra = nombreUnidadCompra;
    }

    public Integer getEquivalenciaUnidadCompra() {
        return equivalenciaUnidadCompra;
    }

    public void setEquivalenciaUnidadCompra(Integer equivalenciaUnidadCompra) {
        this.equivalenciaUnidadCompra = equivalenciaUnidadCompra;
    }
}
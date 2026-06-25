package com.tiendasurtida.dto;

public class ProductoMasVendidoDTO {

    private String nombreProducto;
    private Long cantidadVendida;
//constructores
    public ProductoMasVendidoDTO() {
    }

    public ProductoMasVendidoDTO(String nombreProducto, Long cantidadVendida) {
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
    }
//getter y setter
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}

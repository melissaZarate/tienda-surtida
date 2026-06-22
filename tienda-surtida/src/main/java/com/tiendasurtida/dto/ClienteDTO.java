package com.tiendasurtida.dto;

public class ClienteDTO {

    private Integer ciCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefonoCliente;
    private String direccionCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer ciCliente, String nombreCliente, String apellidoCliente, String telefonoCliente, String direccionCliente) {
        this.ciCliente = ciCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
    }



    // getters y setters

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
}
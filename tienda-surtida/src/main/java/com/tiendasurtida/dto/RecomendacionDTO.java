
package com.tiendasurtida.dto;

public class RecomendacionDTO {

    private String mensaje;
    private String tipo; // INFO, WARING, SUCCESS

    public RecomendacionDTO() {
    }
  //CONSTRUCCIONES
    public RecomendacionDTO(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
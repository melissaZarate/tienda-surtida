
package com.tiendasurtida.dto;

import java.math.BigDecimal;

public class CajaDTO {

    private BigDecimal montoInicial;

    public CajaDTO() {
    }

    public CajaDTO(BigDecimal montoInicial) {

        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoInicial() {

        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {

        this.montoInicial = montoInicial;
    }
}
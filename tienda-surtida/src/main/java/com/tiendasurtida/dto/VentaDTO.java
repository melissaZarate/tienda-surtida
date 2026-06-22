
package com.tiendasurtida.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VentaDTO {

    private List<ItemVentaDTO> items = new ArrayList<>();

    private BigDecimal total = BigDecimal.ZERO;

    public VentaDTO() {
    }

    public VentaDTO(List<ItemVentaDTO> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

    public List<ItemVentaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemVentaDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
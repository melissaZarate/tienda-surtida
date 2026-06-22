
package com.tiendasurtida.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VentaDTO {

    private List<ItemVentaDTO> items = new ArrayList<>();

    private BigDecimal total = BigDecimal.ZERO;
    private ClienteDTO cliente;

    public VentaDTO() {
    }

    public VentaDTO(List<ItemVentaDTO> items, BigDecimal total, ClienteDTO cliente) {
        this.items = items;
        this.total = total;
        this.cliente = cliente;
    }

    public List<ItemVentaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemVentaDTO> items) {
        this.items = items;
    }
    public BigDecimal calcularTotal(){
        return items.stream().map(ItemVentaDTO::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
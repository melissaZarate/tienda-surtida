package com.tiendasurtida.service;

import com.tiendasurtida.entity.DetalleCompra;

public interface DetalleCompraService {

    void agregarDetalle(Long idCompra, DetalleCompra detalle);
}

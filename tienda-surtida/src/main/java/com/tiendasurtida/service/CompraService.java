package com.tiendasurtida.service;

import com.tiendasurtida.entity.Compra;
import com.tiendasurtida.entity.DetalleCompra;

import java.math.BigDecimal;
import java.util.List;

public interface CompraService {

    Compra crearCompra(Compra compra);

    Compra obtenerPorId(Long id);
    Compra actualizarCompra(Compra compra);

    List<Compra> listarTodas();

    //agragr detalle aqui lalogica del negocio
    void agregarDetalle(Long idCompra, DetalleCompra detalle, BigDecimal precioVentaFinal);
    void finalizarCompra(Long id);

}
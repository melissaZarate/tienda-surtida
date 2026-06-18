package com.tiendasurtida.service;

import com.tiendasurtida.entity.HistorialPrecio;

import java.util.List;

public interface HistorialPrecioService {

    List<HistorialPrecio> listarHistorial();

    List<HistorialPrecio> listarPorProducto(Long idProducto);
    long contarTodos();

    long contarCompras();

    long contarAjustesManuales();
}
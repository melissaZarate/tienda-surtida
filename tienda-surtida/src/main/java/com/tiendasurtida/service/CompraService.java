package com.tiendasurtida.service;

import com.tiendasurtida.entity.Compra;

import java.util.List;

public interface CompraService {

    Compra crearCompra(Compra compra);

    Compra obtenerPorId(Long id);

    List<Compra> listarTodas();
}
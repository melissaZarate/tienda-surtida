
package com.tiendasurtida.service;

import com.tiendasurtida.entity.UnidadCompra;

import java.util.List;

public interface UnidadCompraService {

    List<UnidadCompra> listarTodas();

    UnidadCompra buscarPorId(Long id);
}
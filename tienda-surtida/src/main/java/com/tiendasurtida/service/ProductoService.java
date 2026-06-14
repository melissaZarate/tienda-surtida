package com.tiendasurtida.service;

import com.tiendasurtida.entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();

    Producto guardarProducto(Producto producto);

    Producto buscarPorId(Long id);

    List<Producto> buscarPorNombre(String nombre);
}
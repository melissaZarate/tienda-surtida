package com.tiendasurtida.service;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.Categoria;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();

    Producto guardarProducto(Producto producto);

    Producto buscarPorId(Long id);

    List<Producto> buscarPorNombre(String nombre);
    List<Producto> buscarPorCategoria(Integer idCategoria);//funcion para implentar
    List<Producto> listarProductosActivos();
}
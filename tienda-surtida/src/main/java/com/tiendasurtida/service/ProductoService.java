package com.tiendasurtida.service;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.Categoria;
import com.tiendasurtida.dto.DatosAjusteDTO;
import java.math.BigDecimal;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();

    Producto guardarProducto(Producto producto);

    Producto buscarPorId(Long id);

    List<Producto> buscarPorNombre(String nombre);
    List<Producto> buscarPorCategoria(Integer idCategoria);//funcion para implentar
    List<Producto> listarProductosActivos();
    DatosAjusteDTO obtenerDatosAjuste(Long idProducto); //para ajustes de dtos
    void ajustarPrecioManual(Long idProducto,BigDecimal nuevoPorcentajeGanancia);
    void actualizarStockProducto(Long idProducto);
}
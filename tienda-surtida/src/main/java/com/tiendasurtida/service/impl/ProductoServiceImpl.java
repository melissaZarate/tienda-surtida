package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //aquicontiene la logica de negocio
public class ProductoServiceImpl implements ProductoService {

    @Autowired //inyecta automaticamnete el repository
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll(); //obtiene todos los productos
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto); // guardar producto siel id es null se crea o inserta y siel id existe actualiza
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);// buscar por id si encuenentra el producto y sino null
    }
    @Override
    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }
    @Override
    public List<Producto> buscarPorCategoria(Integer idCategoria){
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }
}


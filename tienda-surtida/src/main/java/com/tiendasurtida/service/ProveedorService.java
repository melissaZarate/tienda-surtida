package com.tiendasurtida.service;

import com.tiendasurtida.entity.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> listarProveedores();

    Proveedor guardarProveedor(Proveedor proveedor);

    Proveedor buscarPorId(Long id);

    List<Proveedor> buscarPorNombre(String nombre);


}
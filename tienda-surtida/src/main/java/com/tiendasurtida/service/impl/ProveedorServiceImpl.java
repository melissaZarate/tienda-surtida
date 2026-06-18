package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Proveedor;
import com.tiendasurtida.repository.ProveedorRepository;
import com.tiendasurtida.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Override
    public List<Proveedor> listarProveedores() {
        return repository.findAll();
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Override
    public Proveedor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

   // @Override
    //public List<Proveedor> buscarPorNombre(String nombre) {
      //  return repository.findByNombreProveedorContainingIgnoreCase(nombre);
    //}
    @Override
    public List<Proveedor> buscarPorNombre(String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            return repository.findAll();
        }

        return repository.findByNombreProveedorContainingIgnoreCase(nombre);
    }
  //  @Override
  /*  public List<Proveedor> listarProveedoresActivos() {
        return proveedorRepository.findByEstadoProveedorTrue();
    }*/

    @Override
    public List<Proveedor> listarProveedoresActivos() {
        return repository.findByEstadoProveedorTrue();
    }
}
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor>
    findByNombreProveedorContainingIgnoreCase(String nombre);
   // List<Proveedor> findByNombreProveedorContainingIgnoreCase(String nombre);

}
//ahoraempezamos e+conla bsuqueda deproductos

package com.tiendasurtida.repository;

import com.tiendasurtida.entity.HistorialPrecio;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialPrecioRepository extends JpaRepository<HistorialPrecio, Long> {

    List<HistorialPrecio> findByProductoIdProductoOrderByFechaCambioHistorialDesc(Long idProducto);
    List<HistorialPrecio> findAllByOrderByFechaCambioHistorialDesc();
    long countByMotivoHistorial(String motivo);
}
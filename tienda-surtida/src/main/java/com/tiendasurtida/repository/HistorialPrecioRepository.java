
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.HistorialPrecio;
import com.tiendasurtida.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialPrecioRepository extends JpaRepository<HistorialPrecio, Long> {

    List<HistorialPrecio> findByProductoIdProductoOrderByFechaCambioHistorialDesc(Long idProducto);
    List<HistorialPrecio> findAllByOrderByFechaCambioHistorialDesc();
    long countByMotivoHistorial(String motivo);
    // optional porque puede suceder que no exista un historial con ese precio
    Optional<HistorialPrecio> findFirstByProductoAndPrecioVentaHistorial(Producto producto, BigDecimal precioVentaHistorial);
}
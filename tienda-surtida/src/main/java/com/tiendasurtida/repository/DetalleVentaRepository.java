
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    @Query("""
       SELECT d.producto,
              SUM(d.cantidadDetalle)
       FROM DetalleVenta d
       GROUP BY d.producto
       ORDER BY SUM(d.cantidadDetalle) DESC
       """)
    List<Object[]> obtenerProductosMasVendidos();
}
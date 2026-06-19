
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.VencimientoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VencimientoProductoRepository extends JpaRepository<VencimientoProducto, Long> {

    // 🔹 Todos los lotes de un producto
    List<VencimientoProducto> findByProductoIdProducto(Long idProducto);

    // CLAVE FEFO: ordenados por vencimiento (los primeros en vencer primero)
    List<VencimientoProducto> findByProductoIdProductoOrderByFechaVencimientoAsc(Long idProducto);

    // Lotes próximos a vencer (para dashboard)
    @Query("SELECT v FROM VencimientoProducto v " +
            "WHERE v.fechaVencimiento BETWEEN CURRENT_DATE AND :fechaLimite")
    List<VencimientoProducto> findProximosAVencer(LocalDate fechaLimite);

    //  Lotes ya vencidos
    @Query("SELECT v FROM VencimientoProducto v " +
            "WHERE v.fechaVencimiento < CURRENT_DATE")
    List<VencimientoProducto> findVencidos();

    @Query("""
       SELECT SUM(v.cantidadVencimiento)
       FROM VencimientoProducto v
       WHERE v.producto.idProducto = :idProducto
       """)
    Integer sumarStockPorProducto(Long idProducto);
}
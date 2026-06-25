
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCaja(Caja caja);
    //consultas para el dashboard
    @Query("SELECT COALESCE(SUM(c.totalCompra), 0) FROM Compra c WHERE c.fechaCompra BETWEEN :inicio AND :fin")
    Double obtenerComprasEntreFechas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
    @Query("SELECT COALESCE(SUM(c.totalCompra), 0) FROM Compra c WHERE c.fechaCompra BETWEEN :inicio AND :fin")
    BigDecimal sumarCompras(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

}
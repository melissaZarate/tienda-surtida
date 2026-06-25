
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tiendasurtida.entity.Producto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findAllByOrderByFechaVentaDesc();
    List<Venta> findByCaja(Caja caja); //para obtener datos de vena en caja
//para el dashboard
@Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
BigDecimal obtenerVentasEntreFechas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
//para reportes
@Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
BigDecimal sumarVentas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

}
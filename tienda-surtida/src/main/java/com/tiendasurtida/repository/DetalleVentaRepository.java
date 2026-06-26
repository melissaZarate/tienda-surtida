
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
  /*  @Query("""
       SELECT d.producto,
              SUM(d.cantidadDetalle)
       FROM DetalleVenta d
       GROUP BY d.producto
       ORDER BY SUM(d.cantidadDetalle) DESC
       """)
    List<Object[]> obtenerProductosMasVendidos();*/
    //para productos mas vendidos
    @Query("SELECT p.nombreProducto,SUM(d.cantidadDetalle)FROM DetalleVenta d JOIN d.producto p GROUP BY p.idProducto, p.nombreProducto ORDER BY SUM(d.cantidadDetalle) DESC")
    List<Object[]> obtenerProductosMasVendidos();
    //para reportes e productos mas vendidos segunfecha
    @Query("SELECT d.producto.nombreProducto,SUM(d.cantidadDetalle)FROM DetalleVenta d WHERE d.venta.fechaVenta BETWEEN :inicio AND :fin GROUP BY d.producto.nombreProducto ORDER BY SUM(d.cantidadDetalle) DESC")
    List<Object[]> obtenerProductosMasVendidos(LocalDateTime inicio, LocalDateTime fin);
    //aqui se guarda todos los prodcutos que se vendieron de aqui obtenemos
     //productos vendidos y a que precio y cantidad
    @Query("SELECT dv.producto, dv.precioDetalleVenta, SUM(dv.cantidadDetalle), SUM(dv.precioDetalleVenta * dv.cantidadDetalle) FROM DetalleVenta dv WHERE dv.venta.fechaVenta BETWEEN :fechaInicio AND :fechaFin GROUP BY dv.producto, dv.precioDetalleVenta ORDER BY SUM(dv.precioDetalleVenta * dv.cantidadDetalle) DESC")
    List<Object[]> obtenerRentabilidadProductos(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);

}
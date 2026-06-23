
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tiendasurtida.entity.Producto;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findAllByOrderByFechaVentaDesc();
    List<Venta> findByCaja(Caja caja); //para obtener datos de vena en caja

}
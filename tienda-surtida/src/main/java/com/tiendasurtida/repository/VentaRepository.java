
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tiendasurtida.entity.Producto;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
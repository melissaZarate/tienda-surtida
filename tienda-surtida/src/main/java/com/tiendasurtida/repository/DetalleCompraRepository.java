
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.DetalleCompra;
import com.tiendasurtida.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {

    List<DetalleCompra> findByCompraIdCompra(Long idCompra); //con esto obetnedmos todos los productis de una comrpa
}
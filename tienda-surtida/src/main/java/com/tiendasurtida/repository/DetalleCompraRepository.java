
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.DetalleCompra;
import com.tiendasurtida.entity.Compra;
import com.tiendasurtida.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {

    List<DetalleCompra> findByCompraIdCompra(Long idCompra); //con esto obetnedmos todos los productis de una comrpa
    DetalleCompra findTopByProductoIdProductoOrderByIdDetalleDesc(Long idProducto);
    Optional<DetalleCompra>findFirstByProductoOrderByIdDetalleDesc(Producto producto);
    //DetalleCompra findTopByProductoIdProductoOrderByIdDetalleDesc(Long idProducto);
}
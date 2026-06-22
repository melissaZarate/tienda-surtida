package com.tiendasurtida.repository;

import com.tiendasurtida.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    // Obtener detalles de un pedido
    List<DetallePedido> findByPedido_IdPedido(Long idPedido);

    // Eliminar todos los detalles de un pedido (si se edita)
    void deleteByPedido_IdPedido(Long idPedido);
}
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

        // Buscar pedido pendiente (regla: solo uno activo)
        Optional<Pedido> findByEstadoPedido_NombreEstadoPedido(String nombreEstadoPedido);
        Optional<Pedido> findByEstadoPedido_IdEstadoPedido(Integer idEstado);
        // Validar      si existe pedido pendiente
        boolean existsByEstadoPedido_IdEstadoPedido(Integer idEstadoPedido);
}

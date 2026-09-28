package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

        // Buscar pedido pendiente optional  para evotar cuando haya null
        Optional<Pedido> findByEstadoPedido_NombreEstadoPedido(String nombreEstadoPedido);
        Optional<Pedido> findByEstadoPedido_IdEstadoPedido(Integer idEstado);
        // Validar      si existe pedido pendiente
        boolean existsByEstadoPedido_IdEstadoPedido(Integer idEstadoPedido);
        //pra recomendaciones contar por estado esto usamos en el dashboard
        long countByEstadoPedido_NombreEstadoPedido(String nombreEstado);
        //pra listar por pagina
        //List<Pedido> findAllByOrderByFechaGeneracionPedidoDesc();
        Page<Pedido> findAllByOrderByFechaGeneracionPedidoDesc(Pageable pageable);

}

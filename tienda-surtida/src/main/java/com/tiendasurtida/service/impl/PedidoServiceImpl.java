package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.DetallePedido;
import com.tiendasurtida.entity.EstadoPedido;
import com.tiendasurtida.entity.Pedido;
import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.Usuario;
import com.tiendasurtida.repository.PedidoRepository;
import com.tiendasurtida.repository.EstadoPedidoRepository;
import com.tiendasurtida.repository.DetallePedidoRepository;
import com.tiendasurtida.repository.UsuarioRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.service.EstadoPedidoService;
import com.tiendasurtida.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {


    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final ProductoRepository productoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final UsuarioRepository usuarioRepository;

    //constructos


    public PedidoServiceImpl(PedidoRepository pedidoRepository, EstadoPedidoRepository estadoPedidoRepository, ProductoRepository productoRepository, DetallePedidoRepository detallePedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.productoRepository = productoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    @Transactional
    public Pedido generarPedidoAutomatico(Long idUsuario) {
     /*   Optional<Pedido> pedidoPendiente = pedidoRepository.findByEstadoPedido_IdEstadoPedido(1);
        if (pedidoPendiente.isPresent()) {
            throw new RuntimeException("Ya existe un pedido pendiente de aprobación");
        }*/


//buscar productos con stock baji
        List<Producto> productos = productoRepository.obtenerProductosStockBajo();
        boolean existePedidoPendiente=pedidoRepository.existsByEstadoPedido_IdEstadoPedido(1);
        if(existePedidoPendiente){
            throw new RuntimeException("Ya existe un pedidopendiente de aprobacion"); //evita pedidos extras por recarga
        }
        //cambios encontroller

        if (productos.isEmpty()) {
            throw new RuntimeException("No hay productos con stock bajo");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow());
        pedido.setEstadoPedido(estadoPedidoRepository.findById(1).orElseThrow());
        pedido.setObservacionPedido("Pedido automático");
        pedido.setFechaGeneracionPedido(LocalDateTime.now());

        pedido.setDetalles(new ArrayList<>());

        for (Producto p : productos) {

            int stockMinimo = p.getStockMinimoProducto();
            int stockActual = p.getStockActualProducto();
            int cantidad = stockMinimo - stockActual;
           /* System.out.println("Producto: " + p.getNombreProducto());
            System.out.println("Stock actual: " + stockActual);
            System.out.println("Stock mínimo: " + stockMinimo);
            System.out.println("Cantidad sugerida: " + cantidad);*/


            if (cantidad <= 0) continue;


            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(p);
            detalle.setCantidadDetalle(cantidad);

            pedido.getDetalles().add(detalle);
        }

        return pedidoRepository.save(pedido);
    }
    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existePedidoPendiente() {
        return pedidoRepository.findByEstadoPedido_IdEstadoPedido(1).isPresent();
    }

    @Override
    @Transactional
    public void aprobarPedido(Long idPedido) {
        //buscamos pedido
        Pedido pedido =pedidoRepository.findById(idPedido).orElseThrow(()->new RuntimeException("pedido no encontrado"));
        EstadoPedido estadoAprobado=estadoPedidoRepository.findById(2).orElseThrow(()->new RuntimeException("estado aprobado no encontrado")); //enviamos de tipo objeto
        pedido.setEstadoPedido(estadoAprobado); //cambia el estadodelpedido}}
        pedidoRepository.save(pedido);

    }
    @Override
    @Transactional
    public void rechazarPedido(Long idPedido) {

        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        EstadoPedido estadoRechazado = estadoPedidoRepository.findById(3).orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        pedido.setEstadoPedido(estadoRechazado);
        pedidoRepository.save(pedido);
    }
    @Override
    @Transactional
    public Pedido generarPedidoPorCategoria(Long idUsuario, Long idCategoria) {

        //  Validar si ya existe un pedido pendiente
        Optional<Pedido> pedidoPendiente =
                pedidoRepository.findByEstadoPedido_IdEstadoPedido(1);

        if (pedidoPendiente.isPresent()) {
            throw new RuntimeException("Ya existe un pedido pendiente de aprobación");
        }

        //  Obtener productos con stock bajo filtrados por categoría
        List<Producto> productos =
                productoRepository.obtenerProductosStockBajoPorCategoria(idCategoria);

        if (productos.isEmpty()) {
            throw new RuntimeException("No existen productos con stock bajo en esta categoría");
        }

        //  Obtener estado pendiente
        EstadoPedido estadoPendiente =
                estadoPedidoRepository.findById(1)
                        .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        //  Obtener usuario
        Usuario usuario =
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 5. Crear pedido
        Pedido pedido = new Pedido();
        pedido.setEstadoPedido(estadoPendiente);
        pedido.setUsuario(usuario);
        pedido.setFechaGeneracionPedido(LocalDateTime.now());
        pedido.setObservacionPedido("Pedido generado por categoría ID: " + idCategoria);

        pedido = pedidoRepository.save(pedido);

        //  Crear detalles
        for (Producto producto : productos) {

            int stockMinimo = producto.getStockMinimoProducto();
            int stockActual = producto.getStockActualProducto();

            int cantidadSugerida = stockMinimo - stockActual;

            // seguridad adicional
            if (cantidadSugerida <= 0) {
                continue;
            }

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidadDetalle(cantidadSugerida);

            detallePedidoRepository.save(detalle);
        }

        return pedido;
    }
}
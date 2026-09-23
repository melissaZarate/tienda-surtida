package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.*;
import com.tiendasurtida.repository.PedidoRepository;
import com.tiendasurtida.repository.EstadoPedidoRepository;
import com.tiendasurtida.repository.DetallePedidoRepository;
import com.tiendasurtida.repository.UsuarioRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.service.DetalleCompraService;
import com.tiendasurtida.service.EstadoPedidoService;
import com.tiendasurtida.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private final DetalleCompraService detalleCompraService;

    //constructos


    public PedidoServiceImpl(PedidoRepository pedidoRepository, EstadoPedidoRepository estadoPedidoRepository, ProductoRepository productoRepository, DetallePedidoRepository detallePedidoRepository, UsuarioRepository usuarioRepository, DetalleCompraService detalleCompraService) {
        this.pedidoRepository = pedidoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.productoRepository = productoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.detalleCompraService = detalleCompraService;
    }

    @Override
  @Transactional
  public Pedido generarPedidoAutomaticoPorUsername(String username) {

      Usuario usuario = usuarioRepository.findByUsernameUsuario(username).orElseThrow(() ->
                              new RuntimeException("Usuario no encontrado"));

      Optional<Pedido> pedidoPendiente = pedidoRepository.findByEstadoPedido_IdEstadoPedido(1);

      if (pedidoPendiente.isPresent()) {
          throw new RuntimeException("Ya existe un pedido pendiente de aprobación");
      }

      List<Producto> productos = productoRepository.obtenerProductosStockBajo();

      if (productos.isEmpty()) {
          throw new RuntimeException("No existen productos con stock bajo");
      }

      EstadoPedido estadoPendiente =
              estadoPedidoRepository.findById(1)
                      .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

      Pedido pedido = new Pedido();
      pedido.setEstadoPedido(estadoPendiente);
      pedido.setUsuario(usuario);
      pedido.setFechaGeneracionPedido(LocalDateTime.now());
      pedido.setObservacionPedido("Pedido generado automáticamente");

      pedido = pedidoRepository.save(pedido);
      //cambios
        BigDecimal totalSugerido= BigDecimal.ZERO;

      for (Producto producto : productos) {
          //con esto para cada proiducto que ingrese al for elsistema preguntara : cual fue lauktima compra registrada de este producto?
          DetalleCompra ultimaCompra = detalleCompraService.obtenerUltimaCompraProducto(producto.getIdProducto());
          //ahora comprobamos siexiste esta comrpa
          if (ultimaCompra == null) {
              //si el proiducto no tien ehistorial de compra
          }
          //el producto se sugiere aqui sies null
          DetallePedido detalle = new DetallePedido();

          detalle.setPedido(pedido);
          detalle.setProducto(producto);
          //perosi no lo es
          if (ultimaCompra != null) {
              detalle.setCantidadDetalle(ultimaCompra.getCantidadDetalle());
              detalle.setUnidadCompra(ultimaCompra.getUnidadCompra());
              detalle.setPecioTotalSugerido(ultimaCompra.getPrecioTotalDetalle());

              totalSugerido = totalSugerido.add(ultimaCompra.getPrecioTotalDetalle()); //aquise suma.add  
          }

          detallePedidoRepository.save(detalle);

         /* int cantidadSugerida = producto.getStockMinimoProducto() - producto.getStockActualProducto();

          if (cantidadSugerida <= 0) continue;

          DetallePedido detalle = new DetallePedido();
          detalle.setPedido(pedido);
          detalle.setProducto(producto);
          detalle.setCantidadDetalle(cantidadSugerida);

          detallePedidoRepository.save(detalle);*/
      }
      //ahora guardamos el total del pedido
        pedido.setTotalSugeridoPedido(totalSugerido);
        pedidoRepository.save(pedido);

      return pedido;
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
    public Pedido generarPedidoPorCategoria(String username, Long idCategoria) {

        // 1. Usuario autenticado (REAL)
        Usuario usuario =
                usuarioRepository.findByUsernameUsuario(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Evitar pedidos duplicados
        Optional<Pedido> pedidoPendiente =
                pedidoRepository.findByEstadoPedido_IdEstadoPedido(1);

        if (pedidoPendiente.isPresent()) {
            throw new RuntimeException("Ya existe un pedido pendiente de aprobación");
        }

        // 3. Productos con stock bajo por categoría
        List<Producto> productos =
                productoRepository.obtenerProductosStockBajoPorCategoria(idCategoria);

        if (productos.isEmpty()) {
            throw new RuntimeException("No hay productos con stock bajo en esta categoría");
        }

        // 4. Estado pendiente
        EstadoPedido estadoPendiente =
                estadoPedidoRepository.findById(1)
                        .orElseThrow(() ->
                                new RuntimeException("Estado no encontrado"));

        // 5. Crear pedido
        Pedido pedido = new Pedido();
        pedido.setEstadoPedido(estadoPendiente);
        pedido.setUsuario(usuario); // AQUÍ YA ES EL LOGUEADO
        pedido.setFechaGeneracionPedido(LocalDateTime.now());
        pedido.setObservacionPedido("Pedido por categoría ID: " + idCategoria);

        pedido = pedidoRepository.save(pedido);

        // 6. Detalles
        for (Producto producto : productos) {

            int cantidadSugerida =
                    producto.getStockMinimoProducto() - producto.getStockActualProducto();

            if (cantidadSugerida <= 0) continue;

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidadDetalle(cantidadSugerida);

            detallePedidoRepository.save(detalle);
        }

        return pedido;
    }
    //lsta depedidoentxt
    @Override
    public String generarTextoPedido(Long idPedido) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() ->
                        new RuntimeException("Pedido no encontrado"));

        StringBuilder sb = new StringBuilder();

        sb.append(" PEDIDO DE REPOSICIÓN\n");
        sb.append(" TIENDA SURTIDA\n\n");

        sb.append("Fecha: ").append(pedido.getFechaGeneracionPedido()).append("\n");

        sb.append(" Pedido: ").append(pedido.getIdPedido()).append("\n\n");

        sb.append(" PRODUCTOS\n\n");

        for (DetallePedido detalle : pedido.getDetalles()) {

            sb.append("• ").append(detalle.getProducto().getNombreProducto()).append("\n");

            sb.append("  Cantidad: ")
                    .append(detalle.getCantidadDetalle())
                    .append("\n\n");
        }

        sb.append("--------------------------------\n");
        sb.append("Generado automáticamente por el Sistema Tienda Surtida");

        return sb.toString();
    }
}
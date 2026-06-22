
package com.tiendasurtida.service;

import com.tiendasurtida.entity.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> listarPedidos(); //para devolver el objeto

    Pedido guardarPedido(Pedido pedido);

    Pedido buscarPorId(Long id);

  //  Pedido generarPedidoAutomatico(Long idUsuario);
    boolean existePedidoPendiente();
    void aprobarPedido(Long idPedido); //solo realizar la accion
    void rechazarPedido(Long idPedido);
    //para generar pedido por categoria
    Pedido generarPedidoPorCategoria(String username , Long idCategoria);
    Pedido generarPedidoAutomaticoPorUsername(String username);

   /* Pedido generarPedidoAutomatico(Long idUsuario);

    Pedido generarPedidoPorCategoria(Integer idCategoria, Long idUsuario);
    Pedido aprobarPedido(Long idPedido);

    Pedido rechazarPedido(Long idPedido);*/
}

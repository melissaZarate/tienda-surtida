package com.tiendasurtida.service;

import com.tiendasurtida.entity.DetalleCompra;
import java.util.List;

public interface DetalleCompraService {
    //DetalleCompra guardarDetalle(DetalleCompra detalle);
    List<DetalleCompra> listarPorCompra(Long idCompra);
   //realizamos csmbios, comora service ahora gregara el desalle y detllecompralplasmara


}

package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.*;
import com.tiendasurtida.repository.*;
import com.tiendasurtida.repository.DetalleCompraRepository;
import com.tiendasurtida.service.DetalleCompraService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

import java.math.BigDecimal;

@Service
public class DetalleCompraServiceImpl
        implements DetalleCompraService {
    private final DetalleCompraRepository detalleCompraRepository;

    public DetalleCompraServiceImpl(DetalleCompraRepository detalleCompraRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
    }
    // private final ProductoRepository productoRepository;

  /*
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
//constructor
    public DetalleCompraServiceImpl(DetalleCompraRepository detalleCompraRepository, CompraRepository compraRepository, ProductoRepository productoRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
    }


    @Override
    public DetalleCompra guardarDetalle(
            DetalleCompra detalle) {

        return detalleCompraRepository.save(detalle);
    }*/

    @Override
    public List<DetalleCompra> listarPorCompra(Long idCompra) {

        return detalleCompraRepository.findByCompraIdCompra(idCompra);
    }
    @Override
    public DetalleCompra obtenerUltimaCompraProducto(Long idProducto) {
  //esto para ajuste manual porque necesitamos mostrar el costo acyual, ganancia actual y el precio de venta
        return detalleCompraRepository.findTopByProductoIdProductoOrderByIdDetalleDesc(idProducto);
    }


}
package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.*;
import com.tiendasurtida.repository.*;
import com.tiendasurtida.service.DetalleCompraService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

    private final DetalleCompraRepository detalleRepo;
    private final CompraRepository compraRepo;
    private final ProductoRepository productoRepo;

    public DetalleCompraServiceImpl(DetalleCompraRepository detalleRepo, CompraRepository compraRepo, ProductoRepository productoRepo) {
        this.detalleRepo = detalleRepo;
        this.compraRepo = compraRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    public void agregarDetalle(Long idCompra, DetalleCompra detalle) {

        // validar compra
        Compra compra = compraRepo.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // validar producto
        Producto producto = productoRepo.findById(
                detalle.getProducto().getIdProducto()
        ).orElseThrow(() -> new RuntimeException("Producto no existe"));

        // cantidad mayor a 0
        if (detalle.getCantidadDetalle() <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        // precio mayor a 0
        if (detalle.getPrecioCompraDetalle().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Precio inválido");
        }

        // asosindo con producp
        detalle.setCompra(compra);
        detalle.setProducto(producto);

        // guatrdar detallw
        detalleRepo.save(detalle);

        //actualizar stock
        producto.setStockActualProducto(
                producto.getStockActualProducto() + detalle.getCantidadDetalle()
        );

        productoRepo.save(producto);

        // actializar subtotal de un producto
        double subtotal = detalle.getPrecioCompraDetalle().doubleValue()
                * detalle.getCantidadDetalle();

        compra.setTotalCompra(compra.getTotalCompra() + subtotal);

        compraRepo.save(compra);
    }
}

package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.ItemVentaDTO;
import com.tiendasurtida.dto.VentaDTO;
import com.tiendasurtida.entity.*;
import com.tiendasurtida.repository.*;
import com.tiendasurtida.service.VentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetalleVentaRepository detalleVentaRepository;


    public VentaServiceImpl(VentaRepository ventaRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository, DetalleVentaRepository detalleVentaRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }



    @Override
    @Transactional
    public Venta registrarVenta(VentaDTO ventaDTO, String username) {

        //  Obtener usuario logueado
        Usuario usuario = usuarioRepository.findByUsernameUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //  Crear venta
        Venta venta = new Venta();
        venta.setUsuario(usuario);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setTotalVenta(BigDecimal.ZERO);

        venta = ventaRepository.save(venta);

        BigDecimal totalFinal = BigDecimal.ZERO;

        //  Procesar carrito
        for (ItemVentaDTO item : ventaDTO.getItems()) {

            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // 4. Validar stock
            if (producto.getStockActualProducto() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombreProducto());
            }

            //  Crear detalle
            BigDecimal subtotal = producto.getPrecioVentaProducto()
                    .multiply(BigDecimal.valueOf(item.getCantidad()));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidadDetalle(item.getCantidad());
            detalle.setPrecioDetalleVenta(producto.getPrecioVentaProducto());

            detalleVentaRepository.save(detalle);

            //  Descontar stock
            producto.setStockActualProducto(
                    producto.getStockActualProducto() - item.getCantidad()
            );

            productoRepository.save(producto);

            totalFinal = totalFinal.add(subtotal);
        }

        //  Guardar total final
        venta.setTotalVenta(totalFinal);
        ventaRepository.save(venta);

        return venta;
    }
}
package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Compra;
import com.tiendasurtida.entity.DetalleCompra;
import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.HistorialPrecio;
import com.tiendasurtida.repository.CompraRepository;
import com.tiendasurtida.repository.HistorialPrecioRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.service.CompraService;
import org.springframework.stereotype.Service;
import com.tiendasurtida.repository.DetalleCompraRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final HistorialPrecioRepository historialPrecioRepository ;

    private final DetalleCompraRepository detalleCompraRepository;
  //para historial se modifica el cpntructor


    public CompraServiceImpl(CompraRepository compraRepository, ProductoRepository productoRepository, HistorialPrecioRepository historialPrecioRepository, DetalleCompraRepository detalleCompraRepository) {
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
        this.historialPrecioRepository = historialPrecioRepository;
        this.detalleCompraRepository = detalleCompraRepository;
    }

    @Override
    public Compra crearCompra(Compra compra) {

        compra.setFechaCompra(LocalDateTime.now());

        if (compra.getTotalCompra() == null) {
            compra.setTotalCompra(0.0);
        }
        compra.setEstadoCompra("EN_PROCESO");

        return compraRepository.save(compra);
    }

    @Override
    public Compra obtenerPorId(Long id) {

        return compraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Compra no encontrada"));
    }

    @Override
    public List<Compra> listarTodas() {

        return compraRepository.findAll();
    }

    @Override
    public void agregarDetalle(
            Long idCompra,
            DetalleCompra detalle,
            BigDecimal precioVentaFinal) {

        // Buscar compra
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() ->
                        new RuntimeException("Compra no encontrada"));

        // Buscar producto
        Producto producto = productoRepository.findById(
                        detalle.getProducto().getIdProducto())
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));
        //qui gurdamos los valores en precioventa anerior
      //  BigDecimal precioVentaAnterior = producto.getPrecioVentaProducto();

        // Calcular costo unitario
        BigDecimal costoUnitario = detalle.getPrecioTotalDetalle().divide(BigDecimal.valueOf(detalle.getCantidadDetalle()), 2, RoundingMode.HALF_UP);

        // Guardar costo unitario
        detalle.setPrecioCompraDetalle(costoUnitario);

        // Calcular porcentaje real de ganancia
        BigDecimal porcentajeGananciaReal =
                precioVentaFinal
                        .subtract(costoUnitario)
                        .divide(
                                costoUnitario,
                                4,
                                RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        detalle.setPorcentajeGananciaDetalle(porcentajeGananciaReal.setScale(2,RoundingMode.HALF_UP));
        // Buscar último detalle del producto para historial
        DetalleCompra ultimoDetalle = detalleCompraRepository.findTopByProductoIdProductoOrderByIdDetalleDesc(producto.getIdProducto());

// Solo registrar historial si el producto ya tuvo compras para CASO1
        if (ultimoDetalle != null) {

            HistorialPrecio historial =
                    new HistorialPrecio();

            historial.setProducto(producto);

            historial.setPrecioCompraHistorial(
                    ultimoDetalle.getPrecioCompraDetalle()
            );

            historial.setPrecioVentaHistorial(
                    producto.getPrecioVentaProducto()
            );

            historial.setFechaCambioHistorial(
                    LocalDateTime.now()
            );

            historial.setMotivoHistorial(
                    "Compra con nuevo precio proveedor"
            );

            historialPrecioRepository.save(historial);
        }

        // Actualizar precio de venta del producto
        producto.setPrecioVentaProducto(precioVentaFinal);



        // Actualizar stock
        Integer stockActual =
                producto.getStockActualProducto();

        if (stockActual == null) {
            stockActual = 0;
        }

        int nuevoStock =
                stockActual + detalle.getCantidadDetalle();

        producto.setStockActualProducto(nuevoStock);
        //regla automatica de estado


       // REGLA NUEVA
        if (nuevoStock <= 0) {
            producto.setEstadoProducto(false);
        } else {
            producto.setEstadoProducto(true);
        }


        // Relación detalle-compra
        detalle.setCompra(compra);


        compra.getDetalles().add(detalle);

        // Recalcular total de compra
        BigDecimal total = compra.getDetalles()
                .stream()
                .map(DetalleCompra::getPrecioTotalDetalle)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        compra.setTotalCompra(total.doubleValue());

        // Guardar cambios
        productoRepository.save(producto);
        compraRepository.save(compra);
        if(compra.getEstadoCompra().equals("FINALIZADA")){
            throw new RuntimeException("compra finalizada, no puede modificarse");
        }
    }
    @Override
    public void finalizarCompra(Long id) {

        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        compra.setEstadoCompra("FINALIZADA");

        compraRepository.save(compra);
    }

    @Override
    public Compra actualizarCompra(Compra compra) {

        return compraRepository.save(compra);
    }
}

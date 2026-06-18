package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.HistorialPrecio;
import com.tiendasurtida.entity.DetalleCompra;
import com.tiendasurtida.repository.DetalleCompraRepository;
import com.tiendasurtida.repository.HistorialPrecioRepository;
import com.tiendasurtida.repository.ProductoRepository;
import com.tiendasurtida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiendasurtida.dto.DatosAjusteDTO;
import java.math.BigDecimal;
import jakarta.transaction.Transactional;

import java.util.List;

@Service //aquicontiene la logica de negocio
public class ProductoServiceImpl implements ProductoService {

     //inyecta automaticamnete el repository
    private final ProductoRepository productoRepository;
    private final DetalleCompraRepository detalleCompraRepository;
    private final HistorialPrecioRepository  historialPrecioRepository;

 //constructor


    public ProductoServiceImpl(ProductoRepository productoRepository, DetalleCompraRepository detalleCompraRepository, HistorialPrecioRepository historialPrecioRepository) {
        this.productoRepository = productoRepository;
        this.detalleCompraRepository = detalleCompraRepository;
        this.historialPrecioRepository =   historialPrecioRepository;
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll(); //obtiene todos los productos
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        if(producto.getStockActualProducto()==null){
            producto.setStockActualProducto(0);
        }
        return productoRepository.save(producto); // guardar producto siel id es null se crea o inserta y siel id existe actualiza
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);// buscar por id si encuenentra el producto y sino null
    }
    @Override
    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }
    @Override
    public List<Producto> buscarPorCategoria(Integer idCategoria){
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }
    @Override
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByEstadoProductoTrue();
    }


    @Override
    public DatosAjusteDTO obtenerDatosAjuste(Long idProducto) {

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        DetalleCompra ultimoDetalle =
                detalleCompraRepository
                        .findTopByProductoIdProductoOrderByIdDetalleDesc(idProducto);

        if (ultimoDetalle == null) {

            return new DatosAjusteDTO(
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    producto.getPrecioVentaProducto()
            );
        }

        return new DatosAjusteDTO(
                ultimoDetalle.getPrecioCompraDetalle(),
                ultimoDetalle.getPorcentajeGananciaDetalle(),
                producto.getPrecioVentaProducto()
        );
    }
    @Override
    @Transactional
    public void ajustarPrecioManual(Long idProducto, BigDecimal nuevoPorcentajeGanancia) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        DetalleCompra ultimoDetalle = detalleCompraRepository.findTopByProductoIdProductoOrderByIdDetalleDesc(idProducto);
        if (ultimoDetalle == null) {
            throw new RuntimeException(
                    "El producto no tiene compras registradas");
        }
        BigDecimal precioCompraAnterior = ultimoDetalle.getPrecioCompraDetalle();
        BigDecimal precioVentaAnterior = producto.getPrecioVentaProducto();
        HistorialPrecio historial = new HistorialPrecio();
        historial.setProducto(producto);
        historial.setPrecioCompraHistorial(precioCompraAnterior);
        historial.setPrecioVentaHistorial(precioVentaAnterior);
        historial.setMotivoHistorial("Problemas externos");
        historialPrecioRepository.save(historial);
        BigDecimal nuevoPrecioVenta = precioCompraAnterior.multiply(BigDecimal.ONE.add(nuevoPorcentajeGanancia.divide(BigDecimal.valueOf(100), 4, java.math.RoundingMode.HALF_UP)));

        producto.setPrecioVentaProducto(
                nuevoPrecioVenta.setScale(
                        2,
                        java.math.RoundingMode.HALF_UP
                )
        );

        ultimoDetalle.setPorcentajeGananciaDetalle(
                nuevoPorcentajeGanancia);

        detalleCompraRepository.save(ultimoDetalle);

        productoRepository.save(producto);
    }
}


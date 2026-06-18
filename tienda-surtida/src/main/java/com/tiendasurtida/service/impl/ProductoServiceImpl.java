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
import java.math.RoundingMode;

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

        Producto productos= new Producto();

        if ( productos.getStockActualProducto()<= 0) {
            productos.setEstadoProducto(false);
        } else {
            productos.setEstadoProducto(true);
        }
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

        // Buscar producto
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Obtener último costo de compra
        DetalleCompra ultimoDetalle = detalleCompraRepository
                .findTopByProductoIdProductoOrderByIdDetalleDesc(idProducto);

        if (ultimoDetalle == null) {
            throw new RuntimeException("No existe historial de compras");
        }

        BigDecimal costoCompra = ultimoDetalle.getPrecioCompraDetalle();

        //  VALIDACIONES
        if (costoCompra == null || costoCompra.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Costo de compra inválido");
        }

        if (nuevoPorcentajeGanancia.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("El porcentaje no puede ser negativo");
        }

        // CAPTURAR VALORES ANTES DEL CAMBIO
        BigDecimal precioVentaAnterior = producto.getPrecioVentaProducto();

        // CALCULAR NUEVO PRECIO
        BigDecimal precioVentaNuevo = costoCompra.multiply(BigDecimal.ONE.add(nuevoPorcentajeGanancia.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);

        //  VALIDAR QUE NO SEA MENOR AL COSTO
        if (precioVentaNuevo.compareTo(costoCompra) < 0) {
            throw new RuntimeException("El precio no puede ser menor al costo de compra");
        }

        //  ACTUALIZAR PRODUCTO
        producto.setPrecioVentaProducto(precioVentaNuevo);

        //  ACTUALIZAR DETALLE COMPRA (ganancia)
        ultimoDetalle.setPorcentajeGananciaDetalle(nuevoPorcentajeGanancia);

        // 9. GUARDAR HISTORIAL (ANTES DEL CAMBIO vs DESPUÉS)
        HistorialPrecio historial = new HistorialPrecio();
        historial.setProducto(producto);
        historial.setPrecioCompraHistorial(costoCompra);
        historial.setPrecioVentaHistorial(precioVentaAnterior);
        historial.setMotivoHistorial("Problemas externos");

        historialPrecioRepository.save(historial);

        //  GUARDAR CAMBIOS
        detalleCompraRepository.save(ultimoDetalle);
        productoRepository.save(producto);
    }
    //CONTADOR PARA TARJETA EN LSTA.HTML
    public long contarStockBajo() {
        return productoRepository.findAll()
                .stream()
                .filter(p ->
                        p.getStockActualProducto() != null &&
                                p.getStockMinimoProducto() != null &&
                                p.getStockActualProducto() <= p.getStockMinimoProducto()
                )
                .count();
    }
}


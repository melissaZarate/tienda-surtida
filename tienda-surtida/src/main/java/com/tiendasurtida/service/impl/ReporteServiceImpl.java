package com.tiendasurtida.service.impl;


import com.tiendasurtida.dto.ProductoMasVendidoDTO;
import com.tiendasurtida.dto.RentabilidadProductoDTO;
import com.tiendasurtida.dto.ReporteFinancieroDTO;
import com.tiendasurtida.entity.DetalleCompra;
import com.tiendasurtida.entity.HistorialPrecio;
import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.repository.*;
import com.tiendasurtida.service.ReporteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final HistorialPrecioRepository historialPrecioRepository;
    private final DetalleCompraRepository detalleCompraRepository;

    public ReporteServiceImpl(VentaRepository ventaRepository, CompraRepository compraRepository, ProductoRepository productoRepository, DetalleVentaRepository detalleVentaRepository, HistorialPrecioRepository historialPrecioRepository, DetalleCompraRepository detalleCompraRepository) {
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.historialPrecioRepository = historialPrecioRepository;
        this.detalleCompraRepository = detalleCompraRepository;
    }

    @Override
    public ReporteFinancieroDTO reporteFinanciero(LocalDate inicio, LocalDate fin) {

        LocalDateTime ini = inicio.atStartOfDay();
        LocalDateTime finDt = fin.atTime(23, 59, 59);

        BigDecimal ventas = ventaRepository.sumarVentas(ini, finDt);
        BigDecimal compras = compraRepository.sumarCompras(ini, finDt);

        BigDecimal ganancias = ventas.subtract(compras);

        ReporteFinancieroDTO dto = new ReporteFinancieroDTO();
     //   dto.setFechaFin(fin);
       // dto.setFechaInicio(inicio);
        dto.setTotalVentas(ventas);
        dto.setTotalCompras(compras);
        dto.setGanancias(ganancias);

        return dto;
    }

    @Override
    public List<Producto> reporteStockBajo() {

        return productoRepository.productosStockBajo();

    }
    @Override
    public List<ProductoMasVendidoDTO> obtenerProductosMasVendidos(LocalDate inicio, LocalDate fin) {

        LocalDateTime fechaInicio = inicio.atStartOfDay(); //desde la hora 0
        LocalDateTime fechaFin = fin.atTime(23, 59, 59); //hasta el uktimosegundo de la fecha
        List<Object[]> resultados = detalleVentaRepository.obtenerProductosMasVendidos(fechaInicio, fechaFin);

        List<ProductoMasVendidoDTO> productos = new ArrayList<>();

        for (Object[] fila : resultados) {

            ProductoMasVendidoDTO dto = new ProductoMasVendidoDTO();
            dto.setNombreProducto((String) fila[0]);

            dto.setCantidadVendida(((Number) fila[1]).longValue());
            productos.add(dto);
        }

        return productos;
    }
    @Override
    public List<RentabilidadProductoDTO> obtenerRentabilidadProductos(LocalDateTime inicio, LocalDateTime fin) {

        List<Object[]> resultados = detalleVentaRepository.obtenerRentabilidadProductos(inicio, fin);
        List<RentabilidadProductoDTO> lista = new ArrayList<>(); //lista final
        for (Object[] fila : resultados) { //filas cryudas de la cnsulta //cada fila represena ungrupo de filas
            System.out.println("correeeeeee");
            Producto producto = (Producto) fila[0]; //extraemos los datos
            BigDecimal precioVenta = (BigDecimal) fila[1];
            Long cantidadVendida = (Long) fila[2];
            BigDecimal ingresoGenerado = (BigDecimal) fila[3];

            // Buscar historial para obtener precio de compra
            Optional<HistorialPrecio> historialOpt = historialPrecioRepository.findFirstByProductoAndPrecioVentaHistorial(producto,
                                    precioVenta); //buscamos preciode compra, que precio tenia cuandose vencio a ete preciode venta

        //    BigDecimal precioCompra = BigDecimal.ZERO; //definimos precio de compra por defecto
            BigDecimal precioCompra=null;
            if (historialOpt.isPresent()) {
             precioCompra = historialOpt.get().getPrecioCompraHistorial(); //siexiste lo usamos
            }
            // 2. Si no hay historial → buscar en detalle compra
            if (precioCompra == null) {

                Optional<DetalleCompra> detalleOpt = detalleCompraRepository.findFirstByProductoOrderByIdDetalleDesc(producto);

                if (detalleOpt.isPresent()) {
                    precioCompra = detalleOpt.get().getPrecioCompraDetalle();
                }
            }

// 3. Último fallback
            if (precioCompra == null) {
                precioCompra = BigDecimal.ZERO;
            }

            // Calcular ganancia precioventa-preciocompra)*cantidad ganancia total por productos vndidos
            BigDecimal ganancia = precioVenta.subtract(precioCompra).multiply(BigDecimal.valueOf(cantidadVendida));

            // Crear DTO
            RentabilidadProductoDTO dto = new RentabilidadProductoDTO(); //creamos objeto dto para ingresar ahi los datos
            dto.setProducto(producto.getNombreProducto());
            dto.setPrecioCompra(precioCompra);
            dto.setPrecioVenta(precioVenta);
            dto.setCantidadVendida(cantidadVendida);
            dto.setIngresoGenerado(ingresoGenerado);
            dto.setGanancia(ganancia);

            lista.add(dto); //cada fila se convierte en un dto nuevo
        }  //termina la iteracion

        return lista; //retornamos lista
    }
}

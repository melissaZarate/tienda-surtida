package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.HistorialPrecio;
import com.tiendasurtida.repository.HistorialPrecioRepository;
import com.tiendasurtida.service.HistorialPrecioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialPrecioServiceImpl
        implements HistorialPrecioService {

    private final HistorialPrecioRepository historialPrecioRepository;

    public HistorialPrecioServiceImpl(
            HistorialPrecioRepository historialPrecioRepository) {

        this.historialPrecioRepository = historialPrecioRepository;
    }

    @Override
    public List<HistorialPrecio> listarHistorial() {

        return historialPrecioRepository.findAllByOrderByFechaCambioHistorialDesc();
    }

    @Override
    public List<HistorialPrecio> listarPorProducto(Long idProducto) {

        return historialPrecioRepository.findByProductoIdProductoOrderByFechaCambioHistorialDesc(idProducto);
    }
    @Override
    public long contarTodos() {
        return historialPrecioRepository.count();
    }

    @Override
    public long contarCompras() {
        return historialPrecioRepository.countByMotivoHistorial(
                "Compra con nuevo precio proveedor");
    }

    @Override
    public long contarAjustesManuales() {
        return historialPrecioRepository.countByMotivoHistorial(
                "Problemas externos");
    }
}
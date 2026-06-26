package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.RentabilidadProductoDTO;
import com.tiendasurtida.repository.DetalleVentaRepository;
import com.tiendasurtida.repository.HistorialPrecioRepository;
import com.tiendasurtida.service.RentabilidadService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentabilidadServiceImpl implements RentabilidadService {
  private final  DetalleVentaRepository detalleVentaRepository;
  private final HistorialPrecioRepository historialPrecioRepository;

    // Aquí inyectaremos los repositories
    //constructores




    public RentabilidadServiceImpl(DetalleVentaRepository detalleVentaRepository, HistorialPrecioRepository historialPrecioRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.historialPrecioRepository = historialPrecioRepository;
    }

    @Override
    public List<RentabilidadProductoDTO> obtenerRentabilidad(LocalDateTime inicio, LocalDateTime fin) {

        // Aquí irá toda la lógica
        return null;
    }
}
package com.tiendasurtida.service;

import com.tiendasurtida.dto.RentabilidadProductoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RentabilidadService {

    List<RentabilidadProductoDTO> obtenerRentabilidad(LocalDateTime inicio, LocalDateTime fin);

}
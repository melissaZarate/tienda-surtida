
package com.tiendasurtida.service;

import com.tiendasurtida.dto.ClienteDTO;
import com.tiendasurtida.dto.VentaDTO;
import com.tiendasurtida.entity.Venta;
import com.tiendasurtida.repository.VentaRepository;

import java.util.List;

public interface VentaService {

    Venta registrarVenta(VentaDTO ventaDTO, String username);
    List<Venta> listarVentas();
    Venta obtenerVentaPorId(Long id);
  //  Venta venta=VentaRepository

}
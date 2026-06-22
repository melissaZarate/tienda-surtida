
package com.tiendasurtida.service;

import com.tiendasurtida.dto.VentaDTO;
import com.tiendasurtida.entity.Venta;

public interface VentaService {

    Venta registrarVenta(VentaDTO ventaDTO, String username);

}
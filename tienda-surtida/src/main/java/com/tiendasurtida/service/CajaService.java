
package com.tiendasurtida.service;

import com.tiendasurtida.dto.CajaDTO;
import com.tiendasurtida.entity.Caja;

import java.util.List;

public interface CajaService {

    Caja abrirCaja(CajaDTO cajaDTO);
    Caja cerrarCaja();
    Caja obtenerCajaAbierta();
    List<Caja> listarCajas();
}
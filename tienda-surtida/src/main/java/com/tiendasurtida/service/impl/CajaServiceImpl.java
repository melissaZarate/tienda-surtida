
package com.tiendasurtida.service.impl;

import com.tiendasurtida.dto.CajaDTO;
import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.entity.Venta;
import com.tiendasurtida.repository.CajaRepository;
import com.tiendasurtida.repository.CompraRepository;
import com.tiendasurtida.repository.VentaRepository;
import com.tiendasurtida.service.CajaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;

  //constructor


    public CajaServiceImpl(CajaRepository cajaRepository, VentaRepository ventaRepository, CompraRepository compraRepository) {
        this.cajaRepository = cajaRepository;
        this.ventaRepository = ventaRepository;
        this.compraRepository = compraRepository;
    }

    @Override
    public Caja abrirCaja(CajaDTO cajaDTO) {

        cajaRepository.findByEstadoIgnoreCase("ABIERTA").ifPresent(c -> {throw new RuntimeException("Ya existe una caja abierta");});//validamos
        Caja caja = new Caja();
        caja.setFechaApertura(LocalDateTime.now());
        caja.setMontoInicial(cajaDTO.getMontoInicial());
        caja.setTotalIngresos(BigDecimal.ZERO);
        caja.setTotalEgresos(BigDecimal.ZERO);
        caja.setSaldoFinal(cajaDTO.getMontoInicial());
        caja.setEstado("ABIERTA");

        return cajaRepository.save(caja);
    }

    @Override
    public Caja cerrarCaja() {

        Caja caja = cajaRepository.findByEstadoIgnoreCase("ABIERTA")
                .orElseThrow(() -> new RuntimeException("No existe una caja abierta"));
        BigDecimal ingresos = ventaRepository.findByCaja(caja).stream().map(Venta::getTotalVenta) //busca las ventas de esta caj
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal egresos = compraRepository.findByCaja(caja).stream().map(c -> BigDecimal.valueOf(c.getTotalCompra()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoFinal = caja.getMontoInicial().add(ingresos).subtract(egresos); //operacion
        caja.setFechaCierre(LocalDateTime.now());
        caja.setTotalIngresos(ingresos);
        caja.setTotalEgresos(egresos);
        caja.setSaldoFinal(saldoFinal);
        caja.setEstado("CERRADA");
        return cajaRepository.save(caja);
    }

    @Override
    public Caja obtenerCajaAbierta() {

        return cajaRepository.findByEstadoIgnoreCase("ABIERTA").orElse(null);
    }

    @Override
    public List<Caja> listarCajas() {
        return cajaRepository.findAllByOrderByFechaAperturaDesc(); //cajas nuevas aparecenprimero
    }
}
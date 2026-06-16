package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Compra;
import com.tiendasurtida.repository.CompraRepository;
import com.tiendasurtida.service.CompraService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public Compra crearCompra(Compra compra) {

        // fechav automatica
        compra.setFechaCompra(LocalDateTime.now());

        // total inicial
        if (compra.getTotalCompra() == null) {
            compra.setTotalCompra(0.0);
        }

        return compraRepository.save(compra);
    }

    @Override
    public Compra obtenerPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }

    @Override
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }
}
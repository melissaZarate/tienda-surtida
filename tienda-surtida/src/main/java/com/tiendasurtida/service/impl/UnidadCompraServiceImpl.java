
package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.UnidadCompra;
import com.tiendasurtida.repository.UnidadCompraRepository;
import com.tiendasurtida.service.UnidadCompraService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadCompraServiceImpl implements UnidadCompraService {

    private final UnidadCompraRepository unidadCompraRepository;


    public UnidadCompraServiceImpl(
            UnidadCompraRepository unidadCompraRepository) {

        this.unidadCompraRepository = unidadCompraRepository;
    }


    @Override
    public List<UnidadCompra> listarTodas() {

        return unidadCompraRepository.findAll();
    }


    @Override
    public UnidadCompra buscarPorId(Long id) {

        return unidadCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad de compra no encontrada"));
    }
}
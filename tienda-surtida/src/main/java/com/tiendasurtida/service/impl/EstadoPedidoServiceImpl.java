package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.EstadoPedido;
import com.tiendasurtida.repository.EstadoPedidoRepository;
import com.tiendasurtida.service.EstadoPedidoService;
import org.springframework.stereotype.Service;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoServiceImpl(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    @Override
    public EstadoPedido obtenerPorNombre(String nombre) {

        return estadoPedidoRepository
                .findByNombreEstadoPedido(nombre)
                .orElseThrow(() ->
                        new RuntimeException("Estado no encontrado: " + nombre));
    }

}
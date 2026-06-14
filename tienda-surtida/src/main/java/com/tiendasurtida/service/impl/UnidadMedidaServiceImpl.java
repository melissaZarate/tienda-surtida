package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.UnidadMedida;
import com.tiendasurtida.repository.UnidadMedidaRepository;
import com.tiendasurtida.service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository repository;

    @Override
    public List<UnidadMedida> listarUnidades() {
        return repository.findAll();
    }

    @Override
    public UnidadMedida buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UnidadMedida guardar(UnidadMedida unidad) {
        return repository.save(unidad);
    }
}
package com.tiendasurtida.service.impl;

import com.tiendasurtida.entity.Categoria;
import com.tiendasurtida.repository.CategoriaRepository;
import com.tiendasurtida.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll(); //obtienen todas las categorias
    }

    @Override
    public Categoria buscarPorId(Integer id) { //busca una categoria especifica
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria); //crear nueva categoria, actualizar categoria existente
    }
}
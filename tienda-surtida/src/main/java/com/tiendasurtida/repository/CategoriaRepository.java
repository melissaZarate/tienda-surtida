package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Integer> {

}
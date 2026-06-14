package com.tiendasurtida.service;

import com.tiendasurtida.entity.Categoria;
import java.util.List;

public interface CategoriaService {

    List<Categoria> listarCategorias();//para mostrar tabla

    Categoria buscarPorId(Integer id);//buscara por id para editar

    Categoria guardarCategoria(Categoria categoria);//para crear yeditar

}
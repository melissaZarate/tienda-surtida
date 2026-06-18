package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> { //usamos una interfaz porque spring data jpa genral la impkemebtacion automaticamnete por eso
                                            //en este jpa llamamos a la entidad produncto y su id es de tipo long
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre); //esto es para busqueda
    List<Producto> findByCategoriaIdCategoria(Integer idCategoria ); //esto e spara busqueda de categoria
    List<Producto> findByEstadoProductoTrue();
}
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Producto;
import com.tiendasurtida.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> { //usamos una interfaz porque spring data jpa genral la impkemebtacion automaticamnete por eso
    //en este jpa llamamos a la entidad produncto y su id es de tipo long
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre); //esto es para busqueda

    List<Producto> findByCategoriaIdCategoria(Integer idCategoria); //esto e spara busqueda de categoria

    List<Producto> findByEstadoProductoTrue();

    //buscar oroductos con stock bajo
    List<Producto> findByStockActualProductoLessThanEqualAndEstadoProductoTrue(Integer stockMinimoProducto);

    //con consulta sacamos lista productos con stock bajo
    @Query("SELECT p FROM Producto p WHERE p.estadoProducto = true AND p.stockActualProducto <= p.stockMinimoProducto")
    List<Producto> obtenerProductosStockBajo();
    @Query("SELECT p FROM Producto p WHERE p.stockActualProducto <= p.stockMinimoProducto AND p.estadoProducto = true AND p.categoria.idCategoria = :idCategoria")
    List<Producto> obtenerProductosStockBajoPorCategoria(@Param("idCategoria") Long idCategoria);
    @Query(" SELECT p FROM Producto p WHERE LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> buscarPorNombre(String nombre);
    //consultas para el dashboard
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.estadoProducto = true") //pra el total de productos
    Long contarProductosActivos();
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stockActualProducto <= p.stockMinimoProducto AND p.estadoProducto = true")//productos constock bajo
    Long contarProductosStockBajo();
    //para rportes
    @Query("SELECT p FROM Producto p WHERE p.stockActualProducto <= p.stockMinimoProducto AND p.estadoProducto = true")
    List<Producto> productosStockBajo();

}

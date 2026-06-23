
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Caja;
import com.tiendasurtida.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCaja(Caja caja);
}
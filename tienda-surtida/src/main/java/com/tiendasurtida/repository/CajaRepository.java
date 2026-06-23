
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CajaRepository extends JpaRepository<Caja, Long> {

    Optional<Caja> findByEstado(String estado);
    Optional<Caja> findByEstadoIgnoreCase(String estado);//esto busca la caja con el estado ABIERTO o CERRADO
    List<Caja> findAllByOrderByFechaAperturaDesc();
}
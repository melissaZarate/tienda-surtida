
package com.tiendasurtida.repository;

import com.tiendasurtida.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCiCliente(Integer ciCliente);

}


package com.tiendasurtida.repository;

import com.tiendasurtida.entity.UnidadCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadCompraRepository extends JpaRepository<UnidadCompra, Long> { //pa ghacer cosnutlas, ahora stpring yjpa podran hacer consuktas

}


package com.tiendasurtida.service;

import com.tiendasurtida.entity.UnidadMedida;
import java.util.List;

public interface UnidadMedidaService {

    List<UnidadMedida> listarUnidades();

    UnidadMedida buscarPorId(Integer id);

    UnidadMedida guardar(UnidadMedida unidad);

}
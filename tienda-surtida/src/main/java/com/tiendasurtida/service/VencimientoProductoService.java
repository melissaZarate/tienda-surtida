package com.tiendasurtida.service;
import com.tiendasurtida.dto.VencimientoDTO;
import com.tiendasurtida.entity.VencimientoProducto;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface VencimientoProductoService {
    List<VencimientoDTO> listarProximosAVencer(int dias);

}

package com.tiendasurtida.service.impl;
import java.time.LocalDate;
import java.util.List;
import com.tiendasurtida.service.VencimientoProductoService;
import com.tiendasurtida.entity.VencimientoProducto;
import com.tiendasurtida.repository.VencimientoProductoRepository;
import com.tiendasurtida.dto.VencimientoDTO;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.time.temporal.ChronoUnit;

@Service
public class VencimientoProductoServiceImpl implements VencimientoProductoService {
    private final VencimientoProductoRepository  vencimientoProductoRepository ;

    public VencimientoProductoServiceImpl(VencimientoProductoRepository vencimientoProductoRepository) {
        this.vencimientoProductoRepository = vencimientoProductoRepository;
    }

 /*   @Override
    public List<VencimientoProducto> listarProximosAVencer(int dias) {

        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(dias);

        return vencimientoProductoRepository.findByFechaVencimientoBetween(hoy, limite);
    }*/
    @Override
    public List<VencimientoDTO> listarProximosAVencer(int dias) {

        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(dias);

        List<VencimientoProducto> lotes = vencimientoProductoRepository.findByFechaVencimientoBetween(hoy, limite);

        return lotes.stream()
                .map(l -> new VencimientoDTO(l.getProducto().getNombreProducto(), l.getCantidadVencimiento(),l.getFechaVencimiento(), ChronoUnit.DAYS.between(hoy, l.getFechaVencimiento()))).toList();
    }
}

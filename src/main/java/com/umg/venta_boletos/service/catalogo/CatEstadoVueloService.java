package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import com.umg.venta_boletos.repo.CatEstadoVueloRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatEstadoVueloService {
    private final CatEstadoVueloRepo repo;

    @Transactional(readOnly = true)
    public CatEstadoVuelo getOr404(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("EstadoVuelo " + id + " no existe"));
    }

    @Transactional
    public CatEstadoVuelo save(CatEstadoVuelo e) {
        return repo.save(e);
    }
}

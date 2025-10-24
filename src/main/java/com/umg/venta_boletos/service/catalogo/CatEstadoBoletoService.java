package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import com.umg.venta_boletos.repo.CatEstadoBoletoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatEstadoBoletoService {
    private final CatEstadoBoletoRepo repo;

    @Transactional(readOnly = true)
    public CatEstadoBoleto getOr404(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("EstadoBoleto " + id + " no existe"));
    }

    @Transactional
    public CatEstadoBoleto save(CatEstadoBoleto e) {
        return repo.save(e);
    }
}

package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import com.umg.venta_boletos.repo.CatMetodoPagoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatMetodoPagoService {
    private final CatMetodoPagoRepo repo;

    @Transactional(readOnly = true)
    public CatMetodoPago getOr404(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("MetodoPago " + id + " no existe"));
    }

    @Transactional
    public CatMetodoPago save(CatMetodoPago e) {
        return repo.save(e);
    }
}

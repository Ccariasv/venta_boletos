package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import com.umg.venta_boletos.repo.ClaseAsientoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClaseAsientoService {
    private final ClaseAsientoRepo repo;

    @Transactional(readOnly = true)
    public ClaseAsiento getOr404(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("ClaseAsiento " + id + " no existe"));
    }

    @Transactional
    public ClaseAsiento save(ClaseAsiento e) {
        return repo.save(e);
    }
}

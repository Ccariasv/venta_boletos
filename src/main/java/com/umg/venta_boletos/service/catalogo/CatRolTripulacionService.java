package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import com.umg.venta_boletos.repo.CatRolTripulacionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatRolTripulacionService {
    private final CatRolTripulacionRepo repo;

    @Transactional(readOnly = true)
    public CatRolTripulacion getOr404(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("RolTripulacion " + id + " no existe"));
    }

    @Transactional
    public CatRolTripulacion save(CatRolTripulacion e) {
        return repo.save(e);
    }
}

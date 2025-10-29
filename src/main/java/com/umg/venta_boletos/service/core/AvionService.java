package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Avion;
import com.umg.venta_boletos.repo.AvionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class AvionService {
    private final AvionRepo repo;

    @Transactional(readOnly = true)
    public Avion getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Avion "+id+" no existe"));
    }

    @Transactional
    public Avion saveValid(Avion a){
        if(a.getCapacidadTotal()==null || a.getCapacidadTotal()<=0)
            throw new BusinessException("Capacidad total debe ser > 0");
        return repo.save(a);
    }
}

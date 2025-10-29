package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Asiento;
import com.umg.venta_boletos.repo.AsientoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class AsientoService {
    private final AsientoRepo repo;

    @Transactional(readOnly = true)
    public Asiento getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Asiento "+id+" no existe"));
    }

    @Transactional
    public Asiento saveValid(Asiento a){
        if(a.getNumeroAsiento()==null || a.getNumeroAsiento().isBlank())
            throw new BusinessException("Numero de asiento es requerido");
        return repo.save(a);
    }
}

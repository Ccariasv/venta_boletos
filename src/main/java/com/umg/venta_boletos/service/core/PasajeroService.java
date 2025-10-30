package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Pasajero;
import com.umg.venta_boletos.repo.PasajeroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class PasajeroService {
    private final PasajeroRepo repo;

    @Transactional(readOnly = true)
    public Pasajero getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Pasajero "+id+" no existe"));
    }

    @Transactional
    public Pasajero save(Pasajero p){ return repo.save(p); }
}

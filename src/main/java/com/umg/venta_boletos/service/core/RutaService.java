package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Ruta;
import com.umg.venta_boletos.repo.RutaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class RutaService {
    private final RutaRepo repo;

    @Transactional(readOnly = true)
    public Ruta getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Ruta "+id+" no existe"));
    }

    @Transactional
    public Ruta saveValid(Ruta r){
        if(r.getOrigen().getId().equals(r.getDestino().getId()))
            throw new BusinessException("Origen y destino no pueden ser iguales");
        if(r.getDuracionMin() == null || r.getDuracionMin() <= 0)
            throw new BusinessException("Duración (min) debe ser > 0");
        return repo.save(r);
    }
}

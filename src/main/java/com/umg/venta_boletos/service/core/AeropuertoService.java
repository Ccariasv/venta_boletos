package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Aeropuerto;
import com.umg.venta_boletos.repo.AeropuertoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class AeropuertoService {
    private final AeropuertoRepo repo;

    @Transactional(readOnly = true)
    public Aeropuerto getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Aeropuerto "+id+" no existe"));
    }

    @Transactional
    public Aeropuerto saveValid(Aeropuerto a){
        if(a.getCodigoIata()!=null && a.getCodigoIata().length()!=3)
            throw new BusinessException("IATA debe tener 3 letras");
        a.setCodigoIata(a.getCodigoIata()!=null? a.getCodigoIata().toUpperCase():null);
        return repo.save(a);
    }
}

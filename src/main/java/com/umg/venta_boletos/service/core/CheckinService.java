package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Checkin;
import com.umg.venta_boletos.repo.CheckinRepo;
import com.umg.venta_boletos.repo.BoletoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class CheckinService {
    private final CheckinRepo repo;
    private final BoletoRepo boletoRepo;

    @Transactional
    public Checkin crear(Checkin c){
        var boleto = boletoRepo.findById(c.getBoleto().getId())
                .orElseThrow(() -> new NotFoundException("Boleto "+c.getBoleto().getId()+" no existe"));
        // UNIQUE en DB (UK_CHECKIN_BOLETO) impide duplicado. Validación adicional:
        repo.findAll().stream()
                .filter(ch -> ch.getBoleto().getId().equals(boleto.getId()))
                .findAny().ifPresent(ch -> { throw new BusinessException("El boleto ya tiene check-in"); });
        if(c.getFechaCheckin()==null) c.setFechaCheckin(LocalDate.now());
        if(!"S".equalsIgnoreCase(c.getEquipajeFlag()) && !"N".equalsIgnoreCase(c.getEquipajeFlag()))
            throw new BusinessException("equipajeFlag debe ser 'S' o 'N'");
        if(c.getPesoTotalKg()!=null && c.getPesoTotalKg()<0)
            throw new BusinessException("pesoTotalKg no puede ser negativo");
        return repo.save(c);
    }

    @Transactional(readOnly = true)
    public Checkin getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Checkin "+id+" no existe"));
    }
}

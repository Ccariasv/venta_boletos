package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Equipaje;
import com.umg.venta_boletos.repo.CheckinRepo;
import com.umg.venta_boletos.repo.EquipajeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class EquipajeService {
    private final EquipajeRepo repo;
    private final CheckinRepo checkinRepo;

    @Transactional
    public Equipaje agregar(Equipaje e){
        var checkin = checkinRepo.findById(e.getCheckin().getId())
                .orElseThrow(() -> new NotFoundException("Checkin "+e.getCheckin().getId()+" no existe"));
        if(e.getPesoKg()==null || e.getPesoKg()<=0)
            throw new BusinessException("pesoKg debe ser > 0");
        return repo.save(e);
    }

    @Transactional(readOnly = true)
    public Equipaje getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Equipaje "+id+" no existe"));
    }
}

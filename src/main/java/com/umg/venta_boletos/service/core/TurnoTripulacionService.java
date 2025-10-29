package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.TurnoTripulacion;
import com.umg.venta_boletos.repo.TurnoTripulacionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class TurnoTripulacionService {
    private final TurnoTripulacionRepo repo;

    @Transactional(readOnly = true)
    public TurnoTripulacion getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Turno "+id+" no existe"));
    }

    @Transactional
    public TurnoTripulacion saveValid(TurnoTripulacion t){
        // UNIQUE(empleado_id, vuelo_id, rol_id) lo garantiza DB, pero validamos user-friendly
        repo.findAll().stream().filter(x ->
                        x.getEmpleado().getId().equals(t.getEmpleado().getId())
                                && x.getVuelo().getId().equals(t.getVuelo().getId())
                                && x.getRol().getId().equals(t.getRol().getId()))
                .findAny().ifPresent(x -> { throw new BusinessException("Ya existe el turno para ese empleado, vuelo y rol"); });

        return repo.save(t);
    }
}

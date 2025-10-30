package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Empleado;
import com.umg.venta_boletos.repo.EmpleadoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepo repo;

    @Transactional(readOnly = true)
    public Empleado getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Empleado "+id+" no existe"));
    }

    @Transactional
    public Empleado save(Empleado e){ return repo.save(e); }
}

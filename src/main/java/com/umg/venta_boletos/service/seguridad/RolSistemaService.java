package com.umg.venta_boletos.service.seguridad;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.seguridad.RolSistema;
import com.umg.venta_boletos.repo.RolSistemaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class RolSistemaService {
    private final RolSistemaRepo repo;
    @Transactional(readOnly = true)
    public RolSistema getOr404(Integer id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Rol "+id+" no existe"));
    }
    @Transactional
    public RolSistema save(RolSistema r){ return repo.save(r); }
}

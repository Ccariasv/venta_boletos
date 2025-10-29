package com.umg.venta_boletos.service.catalogo;

import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import com.umg.venta_boletos.repo.CatEstadoAvionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatEstadoAvionService {
    private final CatEstadoAvionRepo repo;

    @Transactional(readOnly = true)
    public CatEstadoAvion getOr404(Integer id){
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("EstadoAvion " + id + " no existe"));
    }

    @Transactional
    public CatEstadoAvion save(CatEstadoAvion e){
        return repo.save(e);
    }

    @Transactional
    public void delete(Integer id){
        if(!repo.existsById(id)) throw new NotFoundException("EstadoAvion " + id + " no existe");
        repo.deleteById(id);
    }
}

package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.Vuelo;
import com.umg.venta_boletos.repo.VueloRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class VueloService {
    private final VueloRepo repo;
    private final SimpMessagingTemplate ws;

    @Transactional(readOnly = true)
    public Vuelo getOr404(Long id){
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Vuelo "+id+" no existe"));
    }

    @Transactional
    public Vuelo crear(Vuelo v){
        if(!v.getLlegadaTs().isAfter(v.getSalidaTs()))
            throw new BusinessException("llegada_ts debe ser posterior a salida_ts");
        var saved = repo.save(v);
        return saved;
    }

    @Transactional
    public Vuelo actualizar(Long id, Vuelo v){
        if(!repo.existsById(id)) throw new NotFoundException("Vuelo "+id+" no existe");
        if(!v.getLlegadaTs().isAfter(v.getSalidaTs()))
            throw new BusinessException("llegada_ts debe ser posterior a salida_ts");
        v.setId(id);
        return repo.save(v);
    }

    @Transactional
    public Vuelo cambiarEstado(Long id, Integer nuevoEstadoId, String estadoCodigo){
        var v = getOr404(id);
        v.getEstado().setId(nuevoEstadoId); // el mapper te habrá cargado la entidad
        var saved = repo.save(v);
        // Evento WS (tópico será consumido luego)
        ws.convertAndSend("/topic/vuelos.estado",
                new VueloEvent(saved.getId(), saved.getNumeroVuelo(), nuevoEstadoId, estadoCodigo));
        return saved;
    }

    public record VueloEvent(Long vueloId, String numeroVuelo, Integer estadoId, String estadoCodigo) {}
}

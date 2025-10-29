package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service @RequiredArgsConstructor
public class BoletoService {
    private final BoletoRepo boletoRepo;
    private final VueloRepo vueloRepo;
    private final PasajeroRepo pasajeroRepo;
    private final AsientoRepo asientoRepo;
    private final SimpMessagingTemplate ws;

    @Transactional(readOnly = true)
    public Boleto getOr404(Long id){
        return boletoRepo.findById(id).orElseThrow(() -> new NotFoundException("Boleto "+id+" no existe"));
    }

    @Transactional
    public Boleto emitir(Long vueloId, Long pasajeroId, Long asientoId, BigDecimal precio, Integer estadoId){
        var vuelo   = vueloRepo.findById(vueloId).orElseThrow(() -> new NotFoundException("Vuelo "+vueloId+" no existe"));
        var pasajero= pasajeroRepo.findById(pasajeroId).orElseThrow(() -> new NotFoundException("Pasajero "+pasajeroId+" no existe"));
        var asiento = asientoRepo.findById(asientoId).orElseThrow(() -> new NotFoundException("Asiento "+asientoId+" no existe"));

        // Regla: asiento debe pertenecer al avión del vuelo
        if(!asiento.getAvion().getId().equals(vuelo.getAvion().getId()))
            throw new BusinessException("El asiento no pertenece al avión del vuelo");

        // Regla: asiendo no vendido para ese vuelo (además del UNIQUE de DB)
        if(boletoRepo.existsById(asientoId)) { /* no aplica por id; confiamos en UNIQUE (vuelo_id, asiento_id) */ }

        var b = new Boleto();
        b.setVuelo(vuelo);
        b.setPasajero(pasajero);
        b.setAsiento(asiento);
        b.setPrecio(precio);
        // estado se setea por mapper en flujo DTO; aquí por seguridad:
        var estado = new com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto();
        estado.setId(estadoId);
        b.setEstado(estado);

        b = boletoRepo.save(b);

        long vendidos = boletoRepo.countByVuelo_Id(vueloId);
        ws.convertAndSend("/topic/vuelos/" + vueloId + "/asientos",
                new AsientoEvent(vueloId, vendidos));

        return b;
    }

    @Transactional
    public void eliminar(Long id){
        if(!boletoRepo.existsById(id)) throw new NotFoundException("Boleto "+id+" no existe");
        var b = boletoRepo.findById(id).get();
        var vueloId = b.getVuelo().getId();
        boletoRepo.deleteById(id);
        long vendidos = boletoRepo.countByVuelo_Id(vueloId);
        ws.convertAndSend("/topic/vuelos/" + vueloId + "/asientos",
                new AsientoEvent(vueloId, vendidos));
    }

    public record AsientoEvent(Long vueloId, long asientosVendidos) {}
}

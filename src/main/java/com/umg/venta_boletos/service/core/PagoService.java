package com.umg.venta_boletos.service.core;

import com.umg.venta_boletos.common.BusinessException;
import com.umg.venta_boletos.common.NotFoundException;
import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.BoletoRepo;
import com.umg.venta_boletos.repo.PagoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class PagoService {
    private final PagoRepo pagoRepo;
    private final BoletoRepo boletoRepo;

    @Transactional
    public Pago registrar(Pago p){
        if(p.getMonto()==null || p.getMonto().compareTo(BigDecimal.ZERO)<=0)
            throw new BusinessException("El monto debe ser > 0");

        var boleto = boletoRepo.findById(p.getBoleto().getId())
                .orElseThrow(() -> new NotFoundException("Boleto "+p.getBoleto().getId()+" no existe"));

        if(p.getFechaPago()==null) p.setFechaPago(LocalDate.now());

        // (Opcional) Validar que suma de pagos no exceda precio:
        var pagosPrevios = pagoRepo.findAll().stream()
                .filter(pg -> pg.getBoleto().getId().equals(boleto.getId()))
                .map(Pago::getMonto).reduce(BigDecimal.ZERO, BigDecimal::add);
        var total = pagosPrevios.add(p.getMonto());
        if(total.compareTo(boleto.getPrecio()) > 0)
            throw new BusinessException("El total pagado excede el precio del boleto");

        var saved = pagoRepo.save(p);

        // (Opcional) Si alcanzó el total, cambia estado del boleto a EMITIDO (id que uses para 'EMITIDO')
        // Aquí no lo forzamos porque los IDs vienen de tus seeds; podemos parametrizar luego.

        return saved;
    }

    @Transactional(readOnly = true)
    public Pago getOr404(Long id){
        return pagoRepo.findById(id).orElseThrow(() -> new NotFoundException("Pago "+id+" no existe"));
    }
}

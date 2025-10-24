package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Pago;
import com.umg.venta_boletos.web.dto.PagoReq;
import com.umg.venta_boletos.web.dto.PagoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface PagoMapper {

    @Mappings({
            @Mapping(target="boleto", expression="java(ref.refBoleto(req.boletoId()))"),
            @Mapping(target="metodo", expression="java(ref.refCatMetodoPago(req.metodoPagoId()))"),
            @Mapping(target="fechaPago", expression="java(req.fechaPago()==null?java.time.LocalDate.now():req.fechaPago())")
    })
    Pago toEntity(PagoReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="boletoId", source="boleto.id"),
            @Mapping(target="metodoPagoId", source="metodo.id"),
            @Mapping(target="metodoPagoCodigo", source="metodo.codigo")
    })
    PagoRes toRes(Pago e);
}

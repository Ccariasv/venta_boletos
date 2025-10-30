package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Pago;
import com.umg.venta_boletos.web.dto.PagoReq;
import com.umg.venta_boletos.web.dto.PagoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PagoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaPago", ignore = true) // SYSDATE en DB
    @Mapping(target = "boleto", expression = "java(ref.refBoleto(req.boletoId()))")
    @Mapping(target = "metodo", expression = "java(ref.refCatMetodoPago(req.metodoPagoId()))")
    Pago toEntity(PagoReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaPago", ignore = true)
    @Mapping(target = "boleto", expression = "java(req.boletoId()!=null ? ref.refBoleto(req.boletoId()) : entity.getBoleto())")
    @Mapping(target = "metodo", expression = "java(req.metodoPagoId()!=null ? ref.refCatMetodoPago(req.metodoPagoId()) : entity.getMetodo())")
    void update(@MappingTarget Pago entity, PagoReq req, @Context EntityRefResolver ref);

    @Mapping(target = "boletoId",     source = "boleto.id")
    @Mapping(target = "metodoPagoId", source = "metodo.id")
    PagoRes toRes(Pago e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Checkin;
import com.umg.venta_boletos.web.dto.CheckinReq;
import com.umg.venta_boletos.web.dto.CheckinRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CheckinMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCheckin", ignore = true) // SYSDATE en DB
    @Mapping(target = "boleto", expression = "java(ref.refBoleto(req.boletoId()))")
    Checkin toEntity(CheckinReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCheckin", ignore = true) // <-- faltaba, por eso el error
    @Mapping(target = "boleto", expression = "java(req.boletoId()!=null ? ref.refBoleto(req.boletoId()) : entity.getBoleto())")
    void update(@MappingTarget Checkin entity, CheckinReq req, @Context EntityRefResolver ref);

    @Mapping(target = "boletoId", source = "boleto.id")
    CheckinRes toRes(Checkin e);
}

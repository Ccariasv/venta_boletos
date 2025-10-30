package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Ruta;
import com.umg.venta_boletos.web.dto.RutaReq;
import com.umg.venta_boletos.web.dto.RutaRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RutaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "origen",  expression = "java(ref.refAeropuerto(req.origenId()))")
    @Mapping(target = "destino", expression = "java(ref.refAeropuerto(req.destinoId()))")
    Ruta toEntity(RutaReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "origen",  expression = "java(req.origenId()  != null ? ref.refAeropuerto(req.origenId())   : entity.getOrigen())")
    @Mapping(target = "destino", expression = "java(req.destinoId() != null ? ref.refAeropuerto(req.destinoId()) : entity.getDestino())")
    void update(@MappingTarget Ruta entity, RutaReq req, @Context EntityRefResolver ref);

    @Mapping(target = "origenId",  source = "origen.id")
    @Mapping(target = "destinoId", source = "destino.id")
    RutaRes toRes(Ruta e);
}

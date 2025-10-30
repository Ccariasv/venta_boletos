package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Avion;
import com.umg.venta_boletos.web.dto.AvionReq;
import com.umg.venta_boletos.web.dto.AvionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AvionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", expression = "java(ref.refCatEstadoAvion(req.estadoId()))")
    Avion toEntity(AvionReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", expression = "java(req.estadoId()!=null ? ref.refCatEstadoAvion(req.estadoId()) : entity.getEstado())")
    void update(@MappingTarget Avion entity, AvionReq req, @Context EntityRefResolver ref);

    @Mapping(target = "estadoId", source = "estado.id")
    AvionRes toRes(Avion e);
}

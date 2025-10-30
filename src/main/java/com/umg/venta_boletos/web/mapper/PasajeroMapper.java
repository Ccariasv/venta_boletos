package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Pasajero;
import com.umg.venta_boletos.web.dto.PasajeroReq;
import com.umg.venta_boletos.web.dto.PasajeroRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PasajeroMapper {

    @Mapping(target = "id", ignore = true)
    Pasajero toEntity(PasajeroReq req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Pasajero entity, PasajeroReq req);

    PasajeroRes toRes(Pasajero e);
}

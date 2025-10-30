package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Equipaje;
import com.umg.venta_boletos.web.dto.EquipajeReq;
import com.umg.venta_boletos.web.dto.EquipajeRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EquipajeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "checkin", expression = "java(ref.refCheckin(req.checkinId()))")
    Equipaje toEntity(EquipajeReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "checkin", expression = "java(req.checkinId()!=null ? ref.refCheckin(req.checkinId()) : entity.getCheckin())")
    void update(@MappingTarget Equipaje entity, EquipajeReq req, @Context EntityRefResolver ref);

    @Mapping(target = "checkinId", source = "checkin.id")
    EquipajeRes toRes(Equipaje e);
}

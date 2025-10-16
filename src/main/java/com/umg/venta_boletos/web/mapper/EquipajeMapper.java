package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Equipaje;
import com.umg.venta_boletos.web.dto.EquipajeReq;
import com.umg.venta_boletos.web.dto.EquipajeRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface EquipajeMapper {

    @Mappings({
            @Mapping(target="checkin", expression="java(ref.refCheckin(req.checkinId()))")
    })
    Equipaje toEntity(EquipajeReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="checkinId", source="checkin.id")
    })
    EquipajeRes toRes(Equipaje e);
}

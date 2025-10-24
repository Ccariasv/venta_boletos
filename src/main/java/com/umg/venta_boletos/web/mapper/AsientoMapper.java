package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Asiento;
import com.umg.venta_boletos.web.dto.AsientoReq;
import com.umg.venta_boletos.web.dto.AsientoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface AsientoMapper {

    @Mappings({
            @Mapping(target="avion", expression="java(ref.refAvion(req.avionId()))"),
            @Mapping(target="clase", expression="java(ref.refClaseAsiento(req.claseId()))")
    })
    Asiento toEntity(AsientoReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="avionId", source="avion.id"),
            @Mapping(target="avionMatricula", source="avion.matricula"),
            @Mapping(target="claseId", source="clase.id"),
            @Mapping(target="claseNombre", source="clase.nombreClase")
    })
    AsientoRes toRes(Asiento e);
}

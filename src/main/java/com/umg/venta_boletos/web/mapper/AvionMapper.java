package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Avion;
import com.umg.venta_boletos.web.dto.AvionReq;
import com.umg.venta_boletos.web.dto.AvionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface AvionMapper {

    @Mappings({
            @Mapping(target="estado", expression="java(ref.refCatEstadoAvion(req.estadoId()))")
    })
    Avion toEntity(AvionReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="estadoId", source="estado.id"),
            @Mapping(target="estadoCodigo", source="estado.codigo")
    })
    AvionRes toRes(Avion e);
}

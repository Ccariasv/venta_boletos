package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Ruta;
import com.umg.venta_boletos.web.dto.RutaReq;
import com.umg.venta_boletos.web.dto.RutaRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface RutaMapper {

    @Mappings({
            @Mapping(target="origen",  expression="java(ref.refAeropuerto(req.aOrigenId()))"),
            @Mapping(target="destino", expression="java(ref.refAeropuerto(req.aDestinoId()))")
    })
    Ruta toEntity(RutaReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="aOrigenId", source="origen.id"),
            @Mapping(target="aOrigenNombre", source="origen.nombre"),
            @Mapping(target="aOrigenIata", source="origen.codigoIata"),
            @Mapping(target="aDestinoId", source="destino.id"),
            @Mapping(target="aDestinoNombre", source="destino.nombre"),
            @Mapping(target="aDestinoIata", source="destino.codigoIata")
    })
    RutaRes toRes(Ruta e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Vuelo;
import com.umg.venta_boletos.web.dto.VueloReq;
import com.umg.venta_boletos.web.dto.VueloRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface VueloMapper {

    @Mappings({
            @Mapping(target="ruta", expression="java(ref.refRuta(req.rutaId()))"),
            @Mapping(target="avion", expression="java(ref.refAvion(req.avionId()))"),
            @Mapping(target="estado", expression="java(ref.refCatEstadoVuelo(req.estadoId()))")
    })
    Vuelo toEntity(VueloReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="rutaId", source="ruta.id"),
            @Mapping(target="avionId", source="avion.id"),
            @Mapping(target="estadoId", source="estado.id"),
            @Mapping(target="estadoCodigo", source="estado.codigo")
    })
    VueloRes toRes(Vuelo e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Vuelo;
import com.umg.venta_boletos.web.dto.VueloReq;
import com.umg.venta_boletos.web.dto.VueloRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VueloMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ruta",  expression = "java(ref.refRuta(req.rutaId()))")
    @Mapping(target = "avion", expression = "java(ref.refAvion(req.avionId()))")
    @Mapping(target = "estado", expression = "java(ref.refCatEstadoVuelo(req.estadoId()))")
    Vuelo toEntity(VueloReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ruta",  expression = "java(req.rutaId()!=null ? ref.refRuta(req.rutaId())     : entity.getRuta())")
    @Mapping(target = "avion", expression = "java(req.avionId()!=null? ref.refAvion(req.avionId())   : entity.getAvion())")
    @Mapping(target = "estado", expression = "java(req.estadoId()!=null? ref.refCatEstadoVuelo(req.estadoId()): entity.getEstado())")
    void update(@MappingTarget Vuelo entity, VueloReq req, @Context EntityRefResolver ref);

    @Mapping(target = "rutaId",  source = "ruta.id")
    @Mapping(target = "avionId", source = "avion.id")
    @Mapping(target = "estadoId", source = "estado.id")
    VueloRes toRes(Vuelo e);
}

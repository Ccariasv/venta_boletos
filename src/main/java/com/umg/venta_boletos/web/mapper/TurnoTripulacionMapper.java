package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.TurnoTripulacion;
import com.umg.venta_boletos.web.dto.TurnoTripulacionReq;
import com.umg.venta_boletos.web.dto.TurnoTripulacionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface TurnoTripulacionMapper {

    @Mappings({
            @Mapping(target="empleado", expression="java(ref.refEmpleado(req.empleadoId()))"),
            @Mapping(target="vuelo", expression="java(ref.refVuelo(req.vueloId()))"),
            @Mapping(target="rol", expression="java(ref.refCatRolTrip(req.rolId()))")
    })
    TurnoTripulacion toEntity(TurnoTripulacionReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="empleadoId", source="empleado.id"),
            @Mapping(target="empleadoNombre", expression="java(e.getEmpleado().getNombres()+\" \"+e.getEmpleado().getApellidos())"),
            @Mapping(target="vueloId", source="vuelo.id"),
            @Mapping(target="numeroVuelo", source="vuelo.numeroVuelo"),
            @Mapping(target="rolId", source="rol.id"),
            @Mapping(target="rolCodigo", source="rol.codigo")
    })
    TurnoTripulacionRes toRes(TurnoTripulacion e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.TurnoTripulacion;
import com.umg.venta_boletos.web.dto.TurnoTripulacionReq;
import com.umg.venta_boletos.web.dto.TurnoTripulacionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TurnoTripulacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empleado", expression = "java(ref.refEmpleado(req.empleadoId()))")
    @Mapping(target = "vuelo",    expression = "java(ref.refVuelo(req.vueloId()))")
    @Mapping(target = "rol",      expression = "java(ref.refCatRolTrip(req.rolId()))")
    TurnoTripulacion toEntity(TurnoTripulacionReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empleado", expression = "java(req.empleadoId()!=null ? ref.refEmpleado(req.empleadoId()) : entity.getEmpleado())")
    @Mapping(target = "vuelo",    expression = "java(req.vueloId()!=null ? ref.refVuelo(req.vueloId())     : entity.getVuelo())")
    @Mapping(target = "rol",      expression = "java(req.rolId()!=null ? ref.refCatRolTrip(req.rolId())    : entity.getRol())")
    void update(@MappingTarget TurnoTripulacion entity, TurnoTripulacionReq req, @Context EntityRefResolver ref);

    @Mapping(target = "empleadoId", source = "empleado.id")
    @Mapping(target = "vueloId",    source = "vuelo.id")
    @Mapping(target = "rolId",      source = "rol.id")
    TurnoTripulacionRes toRes(TurnoTripulacion e);
}

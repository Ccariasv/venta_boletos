package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Empleado;
import com.umg.venta_boletos.web.dto.EmpleadoReq;
import com.umg.venta_boletos.web.dto.EmpleadoRes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface EmpleadoMapper {

    @Mapping(target = "id", ignore = true)
    Empleado toEntity(EmpleadoReq req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Empleado entity, EmpleadoReq req);

    EmpleadoRes toRes(Empleado e);
}

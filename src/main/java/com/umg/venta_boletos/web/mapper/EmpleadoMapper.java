package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Empleado;
import com.umg.venta_boletos.web.dto.EmpleadoReq;
import com.umg.venta_boletos.web.dto.EmpleadoRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    Empleado toEntity(EmpleadoReq req);
    EmpleadoRes toRes(Empleado e);
}

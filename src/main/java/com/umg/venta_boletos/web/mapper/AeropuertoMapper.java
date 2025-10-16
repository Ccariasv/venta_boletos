package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Aeropuerto;
import com.umg.venta_boletos.web.dto.AeropuertoReq;
import com.umg.venta_boletos.web.dto.AeropuertoRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AeropuertoMapper {
    Aeropuerto toEntity(AeropuertoReq req);
    AeropuertoRes toRes(Aeropuerto e);
}

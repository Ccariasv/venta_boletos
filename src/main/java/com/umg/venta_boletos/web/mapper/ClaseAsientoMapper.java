package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import com.umg.venta_boletos.web.dto.ClaseAsientoReq;
import com.umg.venta_boletos.web.dto.ClaseAsientoRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClaseAsientoMapper {
    ClaseAsiento toEntity(ClaseAsientoReq req);
    ClaseAsientoRes toRes(ClaseAsiento e);
}

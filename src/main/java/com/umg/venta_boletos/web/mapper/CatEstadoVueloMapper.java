package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import com.umg.venta_boletos.web.dto.CatEstadoVueloReq;
import com.umg.venta_boletos.web.dto.CatEstadoVueloRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatEstadoVueloMapper {
    CatEstadoVuelo toEntity(CatEstadoVueloReq req);
    CatEstadoVueloRes toRes(CatEstadoVuelo e);
}

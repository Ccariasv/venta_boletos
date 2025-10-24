package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import com.umg.venta_boletos.web.dto.CatEstadoAvionReq;
import com.umg.venta_boletos.web.dto.CatEstadoAvionRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatEstadoAvionMapper {
    CatEstadoAvion toEntity(CatEstadoAvionReq req);
    CatEstadoAvionRes toRes(CatEstadoAvion e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import com.umg.venta_boletos.web.dto.CatEstadoBoletoReq;
import com.umg.venta_boletos.web.dto.CatEstadoBoletoRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatEstadoBoletoMapper {
    CatEstadoBoleto toEntity(CatEstadoBoletoReq req);
    CatEstadoBoletoRes toRes(CatEstadoBoleto e);
}

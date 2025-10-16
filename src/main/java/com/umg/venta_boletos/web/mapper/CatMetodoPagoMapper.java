package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import com.umg.venta_boletos.web.dto.CatMetodoPagoReq;
import com.umg.venta_boletos.web.dto.CatMetodoPagoRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatMetodoPagoMapper {
    CatMetodoPago toEntity(CatMetodoPagoReq req);
    CatMetodoPagoRes toRes(CatMetodoPago e);
}

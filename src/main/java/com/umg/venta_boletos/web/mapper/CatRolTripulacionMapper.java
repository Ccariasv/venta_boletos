package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import com.umg.venta_boletos.web.dto.CatRolTripulacionReq;
import com.umg.venta_boletos.web.dto.CatRolTripulacionRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatRolTripulacionMapper {
    CatRolTripulacion toEntity(CatRolTripulacionReq req);
    CatRolTripulacionRes toRes(CatRolTripulacion e);
}

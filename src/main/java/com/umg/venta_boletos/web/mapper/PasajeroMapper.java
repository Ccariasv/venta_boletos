package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Pasajero;
import com.umg.venta_boletos.web.dto.PasajeroReq;
import com.umg.venta_boletos.web.dto.PasajeroRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasajeroMapper {
    Pasajero toEntity(PasajeroReq req);
    PasajeroRes toRes(Pasajero e);
}

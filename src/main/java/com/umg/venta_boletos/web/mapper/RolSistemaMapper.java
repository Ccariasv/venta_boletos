package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import com.umg.venta_boletos.web.dto.RolSistemaReq;
import com.umg.venta_boletos.web.dto.RolSistemaRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolSistemaMapper {
    RolSistema toEntity(RolSistemaReq req);
    RolSistemaRes toRes(RolSistema e);
}

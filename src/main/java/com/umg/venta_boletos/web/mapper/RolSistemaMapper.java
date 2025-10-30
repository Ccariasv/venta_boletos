package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import com.umg.venta_boletos.web.dto.RolSistemaReq;
import com.umg.venta_boletos.web.dto.RolSistemaRes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface RolSistemaMapper {

    @Mapping(target = "id", ignore = true)
    RolSistema toEntity(RolSistemaReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget RolSistema e, RolSistemaReq r);

    RolSistemaRes toRes(RolSistema e);
}

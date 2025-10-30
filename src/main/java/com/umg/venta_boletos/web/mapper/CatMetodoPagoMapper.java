// CatMetodoPagoMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import com.umg.venta_boletos.web.dto.CatMetodoPagoReq;
import com.umg.venta_boletos.web.dto.CatMetodoPagoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CatMetodoPagoMapper {
    @Mapping(target = "id", ignore = true)
    CatMetodoPago toEntity(CatMetodoPagoReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CatMetodoPago e, CatMetodoPagoReq r);

    CatMetodoPagoRes toRes(CatMetodoPago e);
}

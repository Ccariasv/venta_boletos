// CatEstadoVueloMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import com.umg.venta_boletos.web.dto.CatEstadoVueloReq;
import com.umg.venta_boletos.web.dto.CatEstadoVueloRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CatEstadoVueloMapper {
    @Mapping(target = "id", ignore = true)
    CatEstadoVuelo toEntity(CatEstadoVueloReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CatEstadoVuelo e, CatEstadoVueloReq r);

    CatEstadoVueloRes toRes(CatEstadoVuelo e);
}

// CatEstadoBoletoMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import com.umg.venta_boletos.web.dto.CatEstadoBoletoReq;
import com.umg.venta_boletos.web.dto.CatEstadoBoletoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CatEstadoBoletoMapper {
    @Mapping(target = "id", ignore = true)
    CatEstadoBoleto toEntity(CatEstadoBoletoReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CatEstadoBoleto e, CatEstadoBoletoReq r);

    CatEstadoBoletoRes toRes(CatEstadoBoleto e);
}

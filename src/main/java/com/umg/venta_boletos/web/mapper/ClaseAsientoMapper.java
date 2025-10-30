// ClaseAsientoMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import com.umg.venta_boletos.web.dto.ClaseAsientoReq;
import com.umg.venta_boletos.web.dto.ClaseAsientoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClaseAsientoMapper {
    @Mapping(target = "id", ignore = true)
    ClaseAsiento toEntity(ClaseAsientoReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget ClaseAsiento e, ClaseAsientoReq r);

    ClaseAsientoRes toRes(ClaseAsiento e);
}

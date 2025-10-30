// CatEstadoAvionMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import com.umg.venta_boletos.web.dto.CatEstadoAvionReq;
import com.umg.venta_boletos.web.dto.CatEstadoAvionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CatEstadoAvionMapper {
    @Mapping(target = "id", ignore = true)
    CatEstadoAvion toEntity(CatEstadoAvionReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CatEstadoAvion e, CatEstadoAvionReq r);

    CatEstadoAvionRes toRes(CatEstadoAvion e);
}

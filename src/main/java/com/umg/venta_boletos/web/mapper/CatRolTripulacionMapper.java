// CatRolTripulacionMapper.java
package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import com.umg.venta_boletos.web.dto.CatRolTripulacionReq;
import com.umg.venta_boletos.web.dto.CatRolTripulacionRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CatRolTripulacionMapper {
    @Mapping(target = "id", ignore = true)
    CatRolTripulacion toEntity(CatRolTripulacionReq r);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CatRolTripulacion e, CatRolTripulacionReq r);

    CatRolTripulacionRes toRes(CatRolTripulacion e);
}

package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Aeropuerto;
import com.umg.venta_boletos.web.dto.AeropuertoReq;
import com.umg.venta_boletos.web.dto.AeropuertoRes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AeropuertoMapper {

    // DTO -> Entity (crear)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigoIata",
            expression = "java(req.codigoIata() != null ? req.codigoIata().toUpperCase() : null)")
    Aeropuerto toEntity(AeropuertoReq req);

    // Entity -> DTO (responder)
    AeropuertoRes toRes(Aeropuerto e);

    // DTO -> Entity (actualizar existente)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigoIata",
            expression = "java(req.codigoIata() != null ? req.codigoIata().toUpperCase() : entity.getCodigoIata())")
    void update(@MappingTarget Aeropuerto entity, AeropuertoReq req);
}

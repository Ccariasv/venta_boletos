package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Asiento;
import com.umg.venta_boletos.web.dto.AsientoReq;
import com.umg.venta_boletos.web.dto.AsientoRes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AsientoMapper {

    // DTO -> Entity (crear)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avion",
            expression = "java(ref.refAvion(req.avionId()))")
    @Mapping(target = "clase",
            expression = "java(ref.refClaseAsiento(req.claseId()))")
    Asiento toEntity(AsientoReq req, @Context EntityRefResolver ref);

    // DTO -> Entity (actualizar existente; ignora nulls)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avion",
            expression = "java(req.avionId() != null ? ref.refAvion(req.avionId()) : entity.getAvion())")
    @Mapping(target = "clase",
            expression = "java(req.claseId() != null ? ref.refClaseAsiento(req.claseId()) : entity.getClase())")
    void update(@MappingTarget Asiento entity, AsientoReq req, @Context EntityRefResolver ref);

    // Entity -> DTO (respuesta)
    @Mapping(target = "avionId", source = "avion.id")
    @Mapping(target = "claseId", source = "clase.id")
    AsientoRes toRes(Asiento e);
}

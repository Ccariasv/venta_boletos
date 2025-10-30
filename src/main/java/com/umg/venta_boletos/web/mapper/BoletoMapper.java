package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Boleto;
import com.umg.venta_boletos.web.dto.BoletoReq;
import com.umg.venta_boletos.web.dto.BoletoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BoletoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaEmision", ignore = true) // SYSDATE en DB
    @Mapping(target = "vuelo",    expression = "java(ref.refVuelo(req.vueloId()))")
    @Mapping(target = "pasajero", expression = "java(ref.refPasajero(req.pasajeroId()))")
    @Mapping(target = "asiento",  expression = "java(ref.refAsiento(req.asientoId()))")
    @Mapping(target = "estado",   expression = "java(ref.refCatEstadoBoleto(req.estadoId()))")
    Boleto toEntity(BoletoReq req, @Context EntityRefResolver ref);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaEmision", ignore = true) // <-- faltaba
    @Mapping(target = "vuelo",    expression = "java(req.vueloId()!=null ? ref.refVuelo(req.vueloId()) : entity.getVuelo())")
    @Mapping(target = "pasajero", expression = "java(req.pasajeroId()!=null ? ref.refPasajero(req.pasajeroId()) : entity.getPasajero())")
    @Mapping(target = "asiento",  expression = "java(req.asientoId()!=null ? ref.refAsiento(req.asientoId()) : entity.getAsiento())")
    @Mapping(target = "estado",   expression = "java(req.estadoId()!=null ? ref.refCatEstadoBoleto(req.estadoId()) : entity.getEstado())")
    void update(@MappingTarget Boleto entity, BoletoReq req, @Context EntityRefResolver ref);

    @Mapping(target = "vueloId",    source = "vuelo.id")
    @Mapping(target = "pasajeroId", source = "pasajero.id")
    @Mapping(target = "asientoId",  source = "asiento.id")
    @Mapping(target = "estadoId",   source = "estado.id")
    BoletoRes toRes(Boleto e);
}

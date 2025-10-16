package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Boleto;
import com.umg.venta_boletos.web.dto.BoletoReq;
import com.umg.venta_boletos.web.dto.BoletoRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface BoletoMapper {

    @Mappings({
            @Mapping(target="vuelo", expression="java(ref.refVuelo(req.vueloId()))"),
            @Mapping(target="pasajero", expression="java(ref.refPasajero(req.pasajeroId()))"),
            @Mapping(target="asiento", expression="java(ref.refAsiento(req.asientoId()))"),
            @Mapping(target="estado", expression="java(ref.refCatEstadoBoleto(req.estadoId()))")
    })
    Boleto toEntity(BoletoReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="vueloId", source="vuelo.id"),
            @Mapping(target="numeroVuelo", source="vuelo.numeroVuelo"),
            @Mapping(target="pasajeroId", source="pasajero.id"),
            @Mapping(target="pasajeroNombre", expression="java(e.getPasajero().getNombres() + \" \" + e.getPasajero().getApellidos())"),
            @Mapping(target="asientoId", source="asiento.id"),
            @Mapping(target="asientoNumero", source="asiento.numeroAsiento"),
            @Mapping(target="estadoId", source="estado.id"),
            @Mapping(target="estadoCodigo", source="estado.codigo")
    })
    BoletoRes toRes(Boleto e);
}

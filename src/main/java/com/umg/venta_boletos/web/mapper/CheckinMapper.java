package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.core.Checkin;
import com.umg.venta_boletos.web.dto.CheckinReq;
import com.umg.venta_boletos.web.dto.CheckinRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface CheckinMapper {

    @Mappings({
            @Mapping(target="boleto", expression="java(ref.refBoleto(req.boletoId()))"),
            @Mapping(target="fechaCheckin", expression="java(req.fechaCheckin()==null?java.time.LocalDate.now():req.fechaCheckin())")
    })
    Checkin toEntity(CheckinReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="boletoId", source="boleto.id")
    })
    CheckinRes toRes(Checkin e);
}

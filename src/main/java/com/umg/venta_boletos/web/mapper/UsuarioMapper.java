package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.seguridad.Usuario;
import com.umg.venta_boletos.web.dto.UsuarioReq;
import com.umg.venta_boletos.web.dto.UsuarioRes;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = EntityRefResolver.class)
public interface UsuarioMapper {

    @Mappings({
            @Mapping(target="rol", expression="java(ref.refRolSistema(req.rolId()))"),
            @Mapping(target="empleado", expression="java(ref.refEmpleado(req.empleadoId()))")
    })
    Usuario toEntity(UsuarioReq req, @Context EntityRefResolver ref);

    @Mappings({
            @Mapping(target="rolId", source="rol.id"),
            @Mapping(target="rolCodigo", source="rol.codigo"),
            @Mapping(target="empleadoId", source="empleado.id")
    })
    UsuarioRes toRes(Usuario e);
}

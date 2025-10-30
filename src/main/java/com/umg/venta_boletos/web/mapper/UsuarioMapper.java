package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import com.umg.venta_boletos.domain.seguridad.Usuario;
import com.umg.venta_boletos.domain.core.Empleado;
import com.umg.venta_boletos.web.dto.UsuarioReq;
import com.umg.venta_boletos.web.dto.UsuarioRes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UsuarioMapper {

    // ---------- toEntity ----------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true) // el servicio setea el hash
    @Mapping(target = "rol", ignore = true)          // se resuelve en @AfterMapping
    @Mapping(target = "empleado", ignore = true)     // se resuelve en @AfterMapping
    @Mapping(target = "activoFlag",
            expression = "java( (req.activo() != null && req.activo()) ? \"S\" : \"N\" )")
    Usuario toEntity(UsuarioReq req, @Context EntityRefResolver ref);

    @AfterMapping
    default void fillRefs(@MappingTarget Usuario u, UsuarioReq req, @Context EntityRefResolver ref){
        if (req == null) return;
        if (req.rolId() != null) {
            u.setRol(ref.refRolSistema(req.rolId()));
        }
        if (req.empleadoId() != null) {
            u.setEmpleado(ref.refEmpleado(req.empleadoId()));
        }
    }

    // ---------- update (PATCH-like) ----------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "activoFlag", ignore = true) // lo ajustamos manualmente abajo
    void update(@MappingTarget Usuario u, UsuarioReq req, @Context EntityRefResolver ref);

    @AfterMapping
    default void afterUpdate(@MappingTarget Usuario u, UsuarioReq req, @Context EntityRefResolver ref){
        if (req == null) return;
        if (req.rolId() != null) {
            u.setRol(ref.refRolSistema(req.rolId()));
        }
        if (req.empleadoId() != null) {
            u.setEmpleado(ref.refEmpleado(req.empleadoId()));
        }
        if (req.activo() != null) {
            u.setActivoFlag(req.activo() ? "S" : "N");
        }
    }

    // ---------- toRes ----------
    @Mapping(target = "rolId", expression = "java(u.getRol() != null ? u.getRol().getId() : null)")
    @Mapping(target = "empleadoId", expression = "java(u.getEmpleado() != null ? u.getEmpleado().getId() : null)")
    @Mapping(target = "activo", expression = "java(u.getActivoFlag() != null && u.getActivoFlag().equalsIgnoreCase(\"S\"))")
    UsuarioRes toRes(Usuario u);
}

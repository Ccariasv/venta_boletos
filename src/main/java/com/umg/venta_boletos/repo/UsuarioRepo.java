package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import org.springframework.data.jpa.repository.JpaRepository;

public class UsuarioRepo extends JpaRepository<RolSistema,Long> {
}

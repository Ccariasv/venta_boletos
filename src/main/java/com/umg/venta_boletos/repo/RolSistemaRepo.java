package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolSistemaRepo extends JpaRepository<RolSistema,Integer> {
    Optional<RolSistema> findByCodigo(Integer codigo);
}

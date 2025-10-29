package com.umg.venta_boletos.repo;

// RolSistemaRepo.java
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.umg.venta_boletos.domain.seguridad.RolSistema;

public interface RolSistemaRepo extends JpaRepository<RolSistema, Integer> {
    Optional<RolSistema> findByCodigo(String codigo); // ✅ firma correcta
}

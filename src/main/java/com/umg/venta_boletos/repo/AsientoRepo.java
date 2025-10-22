package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsientoRepo extends JpaRepository<Asiento,Long> {
}

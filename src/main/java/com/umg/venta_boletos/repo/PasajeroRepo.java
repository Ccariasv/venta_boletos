package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepo extends JpaRepository<Pasajero,Long> {
}

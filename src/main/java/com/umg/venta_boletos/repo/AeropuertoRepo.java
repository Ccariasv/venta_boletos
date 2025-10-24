package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeropuertoRepo extends JpaRepository<Aeropuerto,Long> {
}

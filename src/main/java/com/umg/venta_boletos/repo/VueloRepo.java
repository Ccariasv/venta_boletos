package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepo extends JpaRepository<Vuelo,Long> {
}

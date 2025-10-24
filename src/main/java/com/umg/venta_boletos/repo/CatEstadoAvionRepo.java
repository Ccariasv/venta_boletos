package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatEstadoAvionRepo extends JpaRepository<CatEstadoAvion,Integer> {
}

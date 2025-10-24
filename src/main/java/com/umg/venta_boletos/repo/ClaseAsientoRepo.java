package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseAsientoRepo extends JpaRepository<ClaseAsiento,Long> {
}

package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepo extends JpaRepository<Empleado,Long> {
}

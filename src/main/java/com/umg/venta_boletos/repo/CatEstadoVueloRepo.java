package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.catalogo.*;
import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.domain.seguridad.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatEstadoVueloRepo extends JpaRepository<CatEstadoVuelo,Integer> {
}

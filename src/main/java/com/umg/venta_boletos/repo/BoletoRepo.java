package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepo extends JpaRepository<Boleto, Long> {
    long countByVuelo_Id(Long vueloId);
}

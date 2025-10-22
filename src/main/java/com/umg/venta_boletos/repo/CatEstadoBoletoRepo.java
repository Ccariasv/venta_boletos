package com.umg.venta_boletos.repo;


import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatEstadoBoletoRepo extends JpaRepository<CatEstadoBoleto,Integer> {
}

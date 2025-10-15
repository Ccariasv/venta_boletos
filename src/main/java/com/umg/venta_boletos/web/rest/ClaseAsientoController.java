package com.umg.venta_boletos.web.rest;


import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import com.umg.venta_boletos.repo.ClaseAsientoRepo;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogos/clases-asiento")
class ClaseAsientoController extends BaseCrudController<ClaseAsiento, Long> {
    public ClaseAsientoController(ClaseAsientoRepo repo){ super(repo); }
    @Override protected void setId(ClaseAsiento e, Long id){ e.setId(id); }
}
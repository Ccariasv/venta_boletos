package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Vuelo;
import com.umg.venta_boletos.repo.VueloRepo;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vuelos")
class VueloController extends BaseCrudController<Vuelo, Long> {
    public VueloController(VueloRepo repo){ super(repo); }
    @Override protected void setId(Vuelo e, Long id){ e.setId(id); }
}
package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Avion;
import com.umg.venta_boletos.repo.AvionRepo;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aviones")
class AvionController extends BaseCrudController<Avion, Long> {
    public AvionController(AvionRepo repo){ super(repo); }
    @Override protected void setId(Avion e, Long id){ e.setId(id); }
}
package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/asientos")
class AsientoController extends BaseCrudController<Asiento, Long> {
    public AsientoController(AsientoRepo repo){ super(repo); }
    @Override protected void setId(Asiento e, Long id){ e.setId(id); }
}
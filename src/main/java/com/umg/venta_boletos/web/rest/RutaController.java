package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/rutas")
class RutaController extends BaseCrudController<Ruta, Long> {
    public RutaController(RutaRepo repo){ super(repo); }
    @Override protected void setId(Ruta e, Long id){ e.setId(id); }
}
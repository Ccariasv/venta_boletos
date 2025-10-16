package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/pasajeros")
class PasajeroController extends BaseCrudController<Pasajero, Long> {
    public PasajeroController(PasajeroRepo repo){ super(repo); }
    @Override protected void setId(Pasajero e, Long id){ e.setId(id); }
}
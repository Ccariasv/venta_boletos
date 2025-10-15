package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/aeropuertos")
public class AeropuertoController extends BaseCrudController<Aeropuerto, Long> {
    public AeropuertoController(AeropuertoRepo repo){ super(repo); }
    @Override protected void setId(Aeropuerto e, Long id){ e.setId(id); }
}

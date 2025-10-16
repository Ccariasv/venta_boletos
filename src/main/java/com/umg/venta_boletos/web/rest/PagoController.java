package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/pagos")
class PagoController extends BaseCrudController<Pago, Long> {
    public PagoController(PagoRepo repo){ super(repo); }
    @Override protected void setId(Pago e, Long id){ e.setId(id); }
}
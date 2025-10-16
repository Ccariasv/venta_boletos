package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/boletos")
class BoletoController extends BaseCrudController<Boleto, Long> {
    public BoletoController(BoletoRepo repo){ super(repo); }
    @Override protected void setId(Boleto e, Long id){ e.setId(id); }
}
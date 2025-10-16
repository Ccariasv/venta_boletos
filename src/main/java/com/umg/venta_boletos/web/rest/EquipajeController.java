package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/equipaje")
class EquipajeController extends BaseCrudController<Equipaje, Long> {
    public EquipajeController(EquipajeRepo repo){ super(repo); }
    @Override protected void setId(Equipaje e, Long id){ e.setId(id); }
}
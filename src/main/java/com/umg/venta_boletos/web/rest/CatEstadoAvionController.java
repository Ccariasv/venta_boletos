package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/catalogos/estados-avion")
public class CatEstadoAvionController extends BaseCrudController<CatEstadoAvion, Integer> {
    public CatEstadoAvionController(CatEstadoAvionRepo repo){ super(repo); }
    @Override protected void setId(CatEstadoAvion e, Integer id){ e.setId(id); }
}
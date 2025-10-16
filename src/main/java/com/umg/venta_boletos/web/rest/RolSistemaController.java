package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.seguridad.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/seguridad/roles")
public class RolSistemaController extends BaseCrudController<RolSistema, Integer> {
    public RolSistemaController(RolSistemaRepo repo){ super(repo); }
    @Override protected void setId(RolSistema e, Integer id){ e.setId(id); }
}
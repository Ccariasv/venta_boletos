package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/empleados")
class EmpleadoController extends BaseCrudController<Empleado, Long> {
    public EmpleadoController(EmpleadoRepo repo){ super(repo); }
    @Override protected void setId(Empleado e, Long id){ e.setId(id); }
}
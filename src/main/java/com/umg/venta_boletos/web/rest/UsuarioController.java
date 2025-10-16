package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.seguridad.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/seguridad/usuarios")
class UsuarioController extends BaseCrudController<Usuario, Long> {
    public UsuarioController(UsuarioRepo repo){ super(repo); }
    @Override protected void setId(Usuario e, Long id){ e.setId(id); }
}
package com.umg.venta_boletos.web.rest;
import com.umg.venta_boletos.domain.catalogo.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/catalogos/estados-vuelo")
public class CatEstadoVueloController extends BaseCrudController<CatEstadoVuelo, Integer> {
    public CatEstadoVueloController(CatEstadoVueloRepo repo){ super(repo); }
    @Override protected void setId(CatEstadoVuelo e, Integer id){ e.setId(id); }
}
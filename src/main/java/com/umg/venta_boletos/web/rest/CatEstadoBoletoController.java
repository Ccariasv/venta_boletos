package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import com.umg.venta_boletos.repo.CatEstadoBoletoRepo;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogos/estados-boleto")
class CatEstadoBoletoController extends BaseCrudController<CatEstadoBoleto, Integer> {
    public CatEstadoBoletoController(CatEstadoBoletoRepo repo){ super(repo); }
    @Override protected void setId(CatEstadoBoleto e, Integer id){ e.setId(id); }
}
package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import com.umg.venta_boletos.repo.CatMetodoPagoRepo;
import com.umg.venta_boletos.web.rest.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogos/metodos-pago")
class CatMetodoPagoController extends BaseCrudController<CatMetodoPago, Integer> {
    public CatMetodoPagoController(CatMetodoPagoRepo repo){ super(repo); }
    @Override protected void setId(CatMetodoPago e, Integer id){ e.setId(id); }
}
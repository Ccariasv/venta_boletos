package com.umg.venta_boletos.web.rest;


import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import com.umg.venta_boletos.repo.CatRolTripulacionRepo;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalogos/roles-tripulacion")
class CatRolTripulacionController extends BaseCrudController<CatRolTripulacion, Integer> {
    public CatRolTripulacionController(CatRolTripulacionRepo repo){ super(repo); }
    @Override protected void setId(CatRolTripulacion e, Integer id){ e.setId(id); }
}
package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.repo.*;
import com.umg.venta_boletos.web.base.BaseCrudController;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/checkin")
class CheckinController extends BaseCrudController<Checkin, Long> {
    public CheckinController(CheckinRepo repo){ super(repo); }
    @Override protected void setId(Checkin e, Long id){ e.setId(id); }
}
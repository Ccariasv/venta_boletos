package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import com.umg.venta_boletos.repo.CatMetodoPagoRepo;
import com.umg.venta_boletos.service.catalogo.CatMetodoPagoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatMetodoPagoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/catalogos/metodos-pago")
@RequiredArgsConstructor
public class CatMetodoPagoController {
    private final CatMetodoPagoRepo repo;
    private final CatMetodoPagoService service;
    private final CatMetodoPagoMapper mapper;

    @GetMapping
    public PageResponse<CatMetodoPagoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatMetodoPagoRes get(@PathVariable Integer id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatMetodoPagoRes create(@Valid @RequestBody CatMetodoPagoReq req){
        var e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public CatMetodoPagoRes update(@PathVariable Integer id, @Valid @RequestBody CatMetodoPagoReq req){
        service.getOr404(id);
        var e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

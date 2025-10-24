package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.CatMetodoPagoRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatMetodoPagoMapper;
import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/catalogos/metodos-pago")
@RequiredArgsConstructor
public class CatMetodoPagoController {
    private final CatMetodoPagoRepo repo;
    private final CatMetodoPagoMapper mapper;

    @GetMapping
    public PageResponse<CatMetodoPagoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatMetodoPagoRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatMetodoPagoRes create(@Valid @RequestBody CatMetodoPagoReq req){
        CatMetodoPago e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public CatMetodoPagoRes update(@PathVariable Integer id, @Valid @RequestBody CatMetodoPagoReq req){
        if(!repo.existsById(id)) throw notFound();
        CatMetodoPago e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

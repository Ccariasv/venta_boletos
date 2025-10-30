package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import com.umg.venta_boletos.repo.CatEstadoVueloRepo;
import com.umg.venta_boletos.service.catalogo.CatEstadoVueloService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatEstadoVueloMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/catalogos/estados-vuelo")
@RequiredArgsConstructor
public class CatEstadoVueloController {
    private final CatEstadoVueloRepo repo;
    private final CatEstadoVueloService service;
    private final CatEstadoVueloMapper mapper;

    @GetMapping
    public PageResponse<CatEstadoVueloRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatEstadoVueloRes get(@PathVariable Integer id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatEstadoVueloRes create(@Valid @RequestBody CatEstadoVueloReq req){
        var e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public CatEstadoVueloRes update(@PathVariable Integer id, @Valid @RequestBody CatEstadoVueloReq req){
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

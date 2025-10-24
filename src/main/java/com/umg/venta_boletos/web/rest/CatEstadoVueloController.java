package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.CatEstadoVueloRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatEstadoVueloMapper;
import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/catalogos/estados-vuelo")
@RequiredArgsConstructor
public class CatEstadoVueloController {
    private final CatEstadoVueloRepo repo;
    private final CatEstadoVueloMapper mapper;

    @GetMapping
    public PageResponse<CatEstadoVueloRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatEstadoVueloRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatEstadoVueloRes create(@Valid @RequestBody CatEstadoVueloReq req){
        CatEstadoVuelo e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public CatEstadoVueloRes update(@PathVariable Integer id, @Valid @RequestBody CatEstadoVueloReq req){
        if(!repo.existsById(id)) throw notFound();
        CatEstadoVuelo e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.CatEstadoAvionRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatEstadoAvionMapper;
import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/catalogos/estados-avion")
@RequiredArgsConstructor
public class CatEstadoAvionController {
    private final CatEstadoAvionRepo repo;
    private final CatEstadoAvionMapper mapper;

    @GetMapping
    public PageResponse<CatEstadoAvionRes> list(Pageable pageable){
        Page<CatEstadoAvionRes> page = repo.findAll(pageable).map(mapper::toRes);
        return toPageResponse(page);
    }

    @GetMapping("/{id}")
    public CatEstadoAvionRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatEstadoAvionRes create(@Valid @RequestBody CatEstadoAvionReq req){
        CatEstadoAvion e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public CatEstadoAvionRes update(@PathVariable Integer id, @Valid @RequestBody CatEstadoAvionReq req){
        if(!repo.existsById(id)) throw notFound();
        CatEstadoAvion e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

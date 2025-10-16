package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.VueloRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import com.umg.venta_boletos.domain.core.Vuelo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/vuelos")
@RequiredArgsConstructor
public class VueloController {
    private final VueloRepo repo;
    private final VueloMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<VueloRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public VueloRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public VueloRes create(@Valid @RequestBody VueloReq req){
        Vuelo e = mapper.toEntity(req, ref);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public VueloRes update(@PathVariable Long id, @Valid @RequestBody VueloReq req){
        if(!repo.existsById(id)) throw notFound();
        Vuelo e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

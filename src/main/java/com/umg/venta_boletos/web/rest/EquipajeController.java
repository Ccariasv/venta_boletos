package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.EquipajeRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import com.umg.venta_boletos.domain.core.Equipaje;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/equipaje")
@RequiredArgsConstructor
public class EquipajeController {
    private final EquipajeRepo repo;
    private final EquipajeMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<EquipajeRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public EquipajeRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public EquipajeRes create(@Valid @RequestBody EquipajeReq req){
        Equipaje e = mapper.toEntity(req, ref);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public EquipajeRes update(@PathVariable Long id, @Valid @RequestBody EquipajeReq req){
        if(!repo.existsById(id)) throw notFound();
        Equipaje e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

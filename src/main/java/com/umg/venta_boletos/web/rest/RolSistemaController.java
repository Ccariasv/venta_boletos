package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.RolSistemaRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.RolSistemaMapper;
import com.umg.venta_boletos.domain.seguridad.RolSistema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/seguridad/roles")
@RequiredArgsConstructor
public class RolSistemaController {
    private final RolSistemaRepo repo;
    private final RolSistemaMapper mapper;

    @GetMapping
    public PageResponse<RolSistemaRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public RolSistemaRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public RolSistemaRes create(@Valid @RequestBody RolSistemaReq req){
        RolSistema e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public RolSistemaRes update(@PathVariable Integer id, @Valid @RequestBody RolSistemaReq req){
        if(!repo.existsById(id)) throw notFound();
        RolSistema e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

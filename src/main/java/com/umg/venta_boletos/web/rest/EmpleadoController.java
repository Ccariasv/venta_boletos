package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.EmpleadoRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.EmpleadoMapper;
import com.umg.venta_boletos.domain.core.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoRepo repo;
    private final EmpleadoMapper mapper;

    @GetMapping
    public PageResponse<EmpleadoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public EmpleadoRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoRes create(@Valid @RequestBody EmpleadoReq req){
        Empleado e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public EmpleadoRes update(@PathVariable Long id, @Valid @RequestBody EmpleadoReq req){
        if(!repo.existsById(id)) throw notFound();
        Empleado e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

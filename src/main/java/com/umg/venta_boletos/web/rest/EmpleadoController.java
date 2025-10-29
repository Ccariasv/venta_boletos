package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Empleado;
import com.umg.venta_boletos.repo.EmpleadoRepo;
import com.umg.venta_boletos.service.core.EmpleadoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoRepo repo;
    private final EmpleadoService service;
    private final EmpleadoMapper mapper;

    @GetMapping
    public PageResponse<EmpleadoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public EmpleadoRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoRes create(@Valid @RequestBody EmpleadoReq req){
        Empleado e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public EmpleadoRes update(@PathVariable Long id, @Valid @RequestBody EmpleadoReq req){
        service.getOr404(id);
        Empleado e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

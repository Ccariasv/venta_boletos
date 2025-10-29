package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.seguridad.RolSistema;
import com.umg.venta_boletos.repo.RolSistemaRepo;
import com.umg.venta_boletos.service.seguridad.RolSistemaService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.RolSistemaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/seguridad/roles")
@RequiredArgsConstructor
public class RolSistemaController {
    private final RolSistemaRepo repo;
    private final RolSistemaService service;
    private final RolSistemaMapper mapper;

    @GetMapping
    public PageResponse<RolSistemaRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public RolSistemaRes get(@PathVariable Integer id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public RolSistemaRes create(@Valid @RequestBody RolSistemaReq req){
        RolSistema r = mapper.toEntity(req);
        return mapper.toRes(service.save(r));
    }

    @PutMapping("/{id}")
    public RolSistemaRes update(@PathVariable Integer id, @Valid @RequestBody RolSistemaReq req){
        service.getOr404(id);
        RolSistema r = mapper.toEntity(req);
        r.setId(id);
        return mapper.toRes(service.save(r));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

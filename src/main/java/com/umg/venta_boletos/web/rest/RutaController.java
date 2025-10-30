package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Ruta;
import com.umg.venta_boletos.repo.RutaRepo;
import com.umg.venta_boletos.service.core.RutaService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/rutas")
@RequiredArgsConstructor
public class RutaController {
    private final RutaRepo repo;
    private final RutaService service;
    private final RutaMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<RutaRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public RutaRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public RutaRes create(@Valid @RequestBody RutaReq req){
        Ruta e = mapper.toEntity(req, ref);
        return mapper.toRes(service.saveValid(e));
    }

    @PutMapping("/{id}")
    public RutaRes update(@PathVariable Long id, @Valid @RequestBody RutaReq req){
        service.getOr404(id);
        Ruta e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(service.saveValid(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

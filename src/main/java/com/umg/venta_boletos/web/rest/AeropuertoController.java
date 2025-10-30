package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Aeropuerto;
import com.umg.venta_boletos.repo.AeropuertoRepo;
import com.umg.venta_boletos.service.core.AeropuertoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.AeropuertoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/aeropuertos")
@RequiredArgsConstructor
public class AeropuertoController {
    private final AeropuertoRepo repo;
    private final AeropuertoService service;
    private final AeropuertoMapper mapper;

    @GetMapping
    public PageResponse<AeropuertoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public AeropuertoRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public AeropuertoRes create(@Valid @RequestBody AeropuertoReq req){
        Aeropuerto e = mapper.toEntity(req);
        return mapper.toRes(service.saveValid(e));
    }

    @PutMapping("/{id}")
    public AeropuertoRes update(@PathVariable Long id, @Valid @RequestBody AeropuertoReq req){
        service.getOr404(id);
        Aeropuerto e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.saveValid(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

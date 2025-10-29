package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Pasajero;
import com.umg.venta_boletos.repo.PasajeroRepo;
import com.umg.venta_boletos.service.core.PasajeroService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.PasajeroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/pasajeros")
@RequiredArgsConstructor
public class PasajeroController {
    private final PasajeroRepo repo;
    private final PasajeroService service;
    private final PasajeroMapper mapper;

    @GetMapping
    public PageResponse<PasajeroRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public PasajeroRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PasajeroRes create(@Valid @RequestBody PasajeroReq req){
        Pasajero e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public PasajeroRes update(@PathVariable Long id, @Valid @RequestBody PasajeroReq req){
        service.getOr404(id);
        Pasajero e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

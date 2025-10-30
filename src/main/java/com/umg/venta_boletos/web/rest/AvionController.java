package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Avion;
import com.umg.venta_boletos.repo.AvionRepo;
import com.umg.venta_boletos.service.core.AvionService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/aviones")
@RequiredArgsConstructor
public class AvionController {
    private final AvionRepo repo;
    private final AvionService service;
    private final AvionMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<AvionRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public AvionRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public AvionRes create(@Valid @RequestBody AvionReq req){
        Avion e = mapper.toEntity(req, ref);
        return mapper.toRes(service.saveValid(e));
    }

    @PutMapping("/{id}")
    public AvionRes update(@PathVariable Long id, @Valid @RequestBody AvionReq req){
        service.getOr404(id);
        Avion e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(service.saveValid(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

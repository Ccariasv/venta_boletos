package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import com.umg.venta_boletos.repo.ClaseAsientoRepo;
import com.umg.venta_boletos.service.catalogo.ClaseAsientoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.ClaseAsientoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/catalogos/clases-asiento")
@RequiredArgsConstructor
public class ClaseAsientoController {
    private final ClaseAsientoRepo repo;
    private final ClaseAsientoService service;
    private final ClaseAsientoMapper mapper;

    @GetMapping
    public PageResponse<ClaseAsientoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public ClaseAsientoRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ClaseAsientoRes create(@Valid @RequestBody ClaseAsientoReq req){
        ClaseAsiento e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public ClaseAsientoRes update(@PathVariable Long id, @Valid @RequestBody ClaseAsientoReq req){
        service.getOr404(id);
        ClaseAsiento e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

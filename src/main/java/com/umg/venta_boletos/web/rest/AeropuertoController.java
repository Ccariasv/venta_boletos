package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.AeropuertoRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.AeropuertoMapper;
import com.umg.venta_boletos.domain.core.Aeropuerto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/aeropuertos")
@RequiredArgsConstructor
public class AeropuertoController {
    private final AeropuertoRepo repo;
    private final AeropuertoMapper mapper;

    @GetMapping
    public PageResponse<AeropuertoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public AeropuertoRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public AeropuertoRes create(@Valid @RequestBody AeropuertoReq req){
        Aeropuerto e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public AeropuertoRes update(@PathVariable Long id, @Valid @RequestBody AeropuertoReq req){
        if(!repo.existsById(id)) throw notFound();
        Aeropuerto e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

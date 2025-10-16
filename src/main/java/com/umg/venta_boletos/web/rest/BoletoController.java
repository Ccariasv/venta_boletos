package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.BoletoRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import com.umg.venta_boletos.domain.core.Boleto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/boletos")
@RequiredArgsConstructor
public class BoletoController {
    private final BoletoRepo repo;
    private final BoletoMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<BoletoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public BoletoRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public BoletoRes create(@Valid @RequestBody BoletoReq req){
        // Regla asiento↔avión estará reforzada luego en Service + trigger DB
        Boleto e = mapper.toEntity(req, ref);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public BoletoRes update(@PathVariable Long id, @Valid @RequestBody BoletoReq req){
        if(!repo.existsById(id)) throw notFound();
        Boleto e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

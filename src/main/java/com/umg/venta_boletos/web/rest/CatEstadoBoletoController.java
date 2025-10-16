package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.CatEstadoBoletoRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatEstadoBoletoMapper;
import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/catalogos/estados-boleto")
@RequiredArgsConstructor
public class CatEstadoBoletoController {
    private final CatEstadoBoletoRepo repo;
    private final CatEstadoBoletoMapper mapper;

    @GetMapping
    public PageResponse<CatEstadoBoletoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatEstadoBoletoRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatEstadoBoletoRes create(@Valid @RequestBody CatEstadoBoletoReq req){
        CatEstadoBoleto e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public CatEstadoBoletoRes update(@PathVariable Integer id, @Valid @RequestBody CatEstadoBoletoReq req){
        if(!repo.existsById(id)) throw notFound();
        CatEstadoBoleto e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

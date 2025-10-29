package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import com.umg.venta_boletos.repo.CatEstadoBoletoRepo;
import com.umg.venta_boletos.service.catalogo.CatEstadoBoletoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatEstadoBoletoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/catalogos/estados-boleto")
@RequiredArgsConstructor
public class CatEstadoBoletoController {
    private final CatEstadoBoletoRepo repo;
    private final CatEstadoBoletoService service;
    private final CatEstadoBoletoMapper mapper;

    @GetMapping
    public PageResponse<CatEstadoBoletoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatEstadoBoletoRes get(@PathVariable Integer id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatEstadoBoletoRes create(@Valid @RequestBody CatEstadoBoletoReq req){
        var e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public CatEstadoBoletoRes update(@PathVariable Integer id, @Valid @RequestBody CatEstadoBoletoReq req){
        service.getOr404(id);
        var e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(service.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

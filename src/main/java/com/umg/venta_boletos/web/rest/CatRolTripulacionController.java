package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import com.umg.venta_boletos.repo.CatRolTripulacionRepo;
import com.umg.venta_boletos.service.catalogo.CatRolTripulacionService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatRolTripulacionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/catalogos/roles-tripulacion")
@RequiredArgsConstructor
public class CatRolTripulacionController {
    private final CatRolTripulacionRepo repo;
    private final CatRolTripulacionService service;
    private final CatRolTripulacionMapper mapper;

    @GetMapping
    public PageResponse<CatRolTripulacionRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatRolTripulacionRes get(@PathVariable Integer id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatRolTripulacionRes create(@Valid @RequestBody CatRolTripulacionReq req){
        var e = mapper.toEntity(req);
        return mapper.toRes(service.save(e));
    }

    @PutMapping("/{id}")
    public CatRolTripulacionRes update(@PathVariable Integer id, @Valid @RequestBody CatRolTripulacionReq req){
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

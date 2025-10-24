package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.CatRolTripulacionRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.CatRolTripulacionMapper;
import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/catalogos/roles-tripulacion")
@RequiredArgsConstructor
public class CatRolTripulacionController {
    private final CatRolTripulacionRepo repo;
    private final CatRolTripulacionMapper mapper;

    @GetMapping
    public PageResponse<CatRolTripulacionRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CatRolTripulacionRes get(@PathVariable Integer id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CatRolTripulacionRes create(@Valid @RequestBody CatRolTripulacionReq req){
        CatRolTripulacion e = mapper.toEntity(req);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public CatRolTripulacionRes update(@PathVariable Integer id, @Valid @RequestBody CatRolTripulacionReq req){
        if(!repo.existsById(id)) throw notFound();
        CatRolTripulacion e = mapper.toEntity(req);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

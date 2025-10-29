package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.TurnoTripulacion;
import com.umg.venta_boletos.repo.TurnoTripulacionRepo;
import com.umg.venta_boletos.service.core.TurnoTripulacionService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/turnos-tripulacion")
@RequiredArgsConstructor
public class TurnoTripulacionController {
    private final TurnoTripulacionRepo repo;
    private final TurnoTripulacionService service;
    private final TurnoTripulacionMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<TurnoTripulacionRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public TurnoTripulacionRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public TurnoTripulacionRes create(@Valid @RequestBody TurnoTripulacionReq req){
        TurnoTripulacion t = mapper.toEntity(req, ref);
        return mapper.toRes(service.saveValid(t));
    }

    @PutMapping("/{id}")
    public TurnoTripulacionRes update(@PathVariable Long id, @Valid @RequestBody TurnoTripulacionReq req){
        service.getOr404(id);
        TurnoTripulacion t = mapper.toEntity(req, ref);
        t.setId(id);
        return mapper.toRes(service.saveValid(t));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

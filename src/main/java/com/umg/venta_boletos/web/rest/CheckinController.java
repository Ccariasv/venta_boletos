package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Checkin;
import com.umg.venta_boletos.repo.CheckinRepo;
import com.umg.venta_boletos.service.core.CheckinService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/checkin")
@RequiredArgsConstructor
public class CheckinController {
    private final CheckinRepo repo;
    private final CheckinService service;
    private final CheckinMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<CheckinRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public CheckinRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CheckinRes create(@Valid @RequestBody CheckinReq req){
        Checkin c = mapper.toEntity(req, ref);
        return mapper.toRes(service.crear(c));
    }

    @PutMapping("/{id}")
    public CheckinRes update(@PathVariable Long id, @Valid @RequestBody CheckinReq req){
        // En muchos sistemas, checkin no se edita; si lo quieres, valida en service
        service.getOr404(id);
        Checkin c = mapper.toEntity(req, ref);
        c.setId(id);
        return mapper.toRes(repo.save(c));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

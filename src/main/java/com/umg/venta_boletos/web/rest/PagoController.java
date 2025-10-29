package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Pago;
import com.umg.venta_boletos.repo.PagoRepo;
import com.umg.venta_boletos.service.core.PagoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoRepo repo;
    private final PagoService service;
    private final PagoMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<PagoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public PagoRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PagoRes create(@Valid @RequestBody PagoReq req){
        Pago p = mapper.toEntity(req, ref);
        return mapper.toRes(service.registrar(p));
    }

    @PutMapping("/{id}")
    public PagoRes update(@PathVariable Long id, @Valid @RequestBody PagoReq req){
        // Update directo (normalmente los pagos no se editan; si lo deseas, monta una regla en service)
        Pago p = mapper.toEntity(req, ref);
        p.setId(id);
        return mapper.toRes(repo.save(p));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        // Si deseas controlar reglas de borrado, crea método en service. Aquí simple:
        repo.deleteById(id);
    }
}

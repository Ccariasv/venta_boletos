package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Boleto;
import com.umg.venta_boletos.repo.BoletoRepo;
import com.umg.venta_boletos.service.core.BoletoService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/boletos")
@RequiredArgsConstructor
public class BoletoController {
    private final BoletoRepo repo;
    private final BoletoService service;
    private final BoletoMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<BoletoRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public BoletoRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public BoletoRes create(@Valid @RequestBody BoletoReq req){
        // Usar servicio para aplicar validaciones y eventos WS
        var b = service.emitir(req.vueloId(), req.pasajeroId(), req.asientoId(),
                req.precio(), req.estadoId());
        return mapper.toRes(b);
    }

    @PutMapping("/{id}")
    public BoletoRes update(@PathVariable Long id, @Valid @RequestBody BoletoReq req){
        // Update directo (no dispara WS), si quieres lo pasamos a service.save() con una función adicional
        Boleto e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.eliminar(id); // dispara evento WS de asientos
    }
}

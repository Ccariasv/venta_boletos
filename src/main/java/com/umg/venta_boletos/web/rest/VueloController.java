package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.core.Vuelo;
import com.umg.venta_boletos.repo.VueloRepo;
import com.umg.venta_boletos.service.core.VueloService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/vuelos")
@RequiredArgsConstructor
public class VueloController {

    private final VueloRepo repo;                // ✅ ahora existe 'repo'
    private final VueloService service;
    private final VueloMapper mapper;
    private final EntityRefResolver ref;

    // ✅ Listar todos los vuelos (GET /api/vuelos?page=0&size=20)
    @GetMapping
    public PageResponse<VueloRes> list(Pageable pageable) {
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    // ✅ Obtener un vuelo por ID
    @GetMapping("/{id}")
    public VueloRes get(@PathVariable Long id) {
        return mapper.toRes(service.getOr404(id));
    }

    // ✅ Crear nuevo vuelo (usa validaciones del service)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VueloRes create(@Valid @RequestBody VueloReq req) {
        Vuelo e = mapper.toEntity(req, ref);
        return mapper.toRes(service.crear(e));
    }

    // ✅ Actualizar vuelo existente
    @PutMapping("/{id}")
    public VueloRes update(@PathVariable Long id, @Valid @RequestBody VueloReq req) {
        Vuelo e = mapper.toEntity(req, ref);
        return mapper.toRes(service.actualizar(id, e));
    }

    // ✅ Cambiar estado (por ejemplo: EN_VUELO, ARRIBADO, etc.)
    @PatchMapping("/{id}/estado")
    public VueloRes cambiarEstado(
            @PathVariable Long id,
            @RequestParam Integer estadoId,
            @RequestParam String estadoCodigo
    ) {
        return mapper.toRes(service.cambiarEstado(id, estadoId, estadoCodigo));
    }

    // ✅ Eliminar vuelo
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.getOr404(id);
        repo.deleteById(id);
    }
}

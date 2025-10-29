package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.domain.seguridad.Usuario;
import com.umg.venta_boletos.repo.UsuarioRepo;
import com.umg.venta_boletos.service.seguridad.UsuarioService;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;

@RestController
@RequestMapping("/api/seguridad/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioRepo repo;
    private final UsuarioService service;
    private final UsuarioMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<UsuarioRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public UsuarioRes get(@PathVariable Long id){
        return mapper.toRes(service.getOr404(id));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRes create(@Valid @RequestBody UsuarioReq req){
        Usuario u = mapper.toEntity(req, ref);
        return mapper.toRes(service.save(u)); // cuando activemos JWT, encripta password aquí
    }

    @PutMapping("/{id}")
    public UsuarioRes update(@PathVariable Long id, @Valid @RequestBody UsuarioReq req){
        service.getOr404(id);
        Usuario u = mapper.toEntity(req, ref);
        u.setId(id);
        return mapper.toRes(service.save(u));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.getOr404(id);
        repo.deleteById(id);
    }
}

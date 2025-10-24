package com.umg.venta_boletos.web.rest;

import com.umg.venta_boletos.repo.UsuarioRepo;
import com.umg.venta_boletos.web.dto.*;
import com.umg.venta_boletos.web.mapper.*;
import com.umg.venta_boletos.domain.seguridad.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.umg.venta_boletos.web.mapper.MapperSupport.toPageResponse;
import static com.umg.venta_boletos.web.rest.CrudUtils.notFound;

@RestController
@RequestMapping("/api/seguridad/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioRepo repo;
    private final UsuarioMapper mapper;
    private final EntityRefResolver ref;

    @GetMapping
    public PageResponse<UsuarioRes> list(Pageable pageable){
        return toPageResponse(repo.findAll(pageable).map(mapper::toRes));
    }

    @GetMapping("/{id}")
    public UsuarioRes get(@PathVariable Long id){
        var e = repo.findById(id).orElseThrow(CrudUtils::notFound);
        return mapper.toRes(e);
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public UsuarioRes create(@Valid @RequestBody UsuarioReq req){
        Usuario e = mapper.toEntity(req, ref);
        return mapper.toRes(repo.save(e));
    }

    @PutMapping("/{id}")
    public UsuarioRes update(@PathVariable Long id, @Valid @RequestBody UsuarioReq req){
        if(!repo.existsById(id)) throw notFound();
        Usuario e = mapper.toEntity(req, ref);
        e.setId(id);
        return mapper.toRes(repo.save(e));
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        if(!repo.existsById(id)) throw notFound();
        repo.deleteById(id);
    }
}

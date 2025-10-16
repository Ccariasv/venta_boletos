package com.umg.venta_boletos.web.base;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public abstract class BaseCrudController<T, ID> {
    protected final JpaRepository<T, ID> repo;

    protected BaseCrudController(JpaRepository<T, ID> repo) { this.repo = repo; }

    @GetMapping
    public Page<T> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @GetMapping("/{id}")
    public T get(@PathVariable ID id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@Valid @RequestBody T entity) {
        return repo.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @Valid @RequestBody T entity) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        setId(entity, id);
        return repo.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable ID id) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repo.deleteById(id);
    }

    /** Cada subclase debe implementar cómo setear el ID en la entidad. */
    protected abstract void setId(T entity, ID id);
}

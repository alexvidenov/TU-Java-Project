package com.example.server.network.controllers;

import com.example.server.network.dtos.BaseDto;
import com.example.server.persistence.entities.BaseEntity;
import com.example.server.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends BaseEntity, D extends BaseDto> {
    private final BaseService<T> svc;

    BaseController(BaseService<T> svc) {
        this.svc = svc;
    }

    abstract T mapToDbRepresentation(D dto);
    abstract D mapToDtoRepresentation(T entity);

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D item) {
        T createdEntity = svc.create(mapToDbRepresentation(item));
        return new ResponseEntity<>(mapToDtoRepresentation(createdEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<D>> list() {
        List<D> entities = svc.getAll().stream().map(this::mapToDtoRepresentation).toList();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable Long id) {
        T entity = svc.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(mapToDtoRepresentation(entity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody D payload) {
        T entity = svc.update(id, mapToDbRepresentation(payload));
        if (entity != null) {
            return new ResponseEntity<>(mapToDtoRepresentation(entity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Boolean deleteResult = svc.delete(id);
        if (deleteResult) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

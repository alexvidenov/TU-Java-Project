package com.example.server.controllers;

import com.example.server.persistence.entities.BaseEntity;
import com.example.server.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends BaseEntity> {
    private final BaseService<T> svc;

    BaseController(BaseService<T> svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T item) {
        T createdEntity = svc.create(item);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<T>> list() {
        List<T> entities = svc.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        T entity = svc.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T updatedUser) {
        T entity = svc.update(id, updatedUser);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
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

package com.example.server.services;

import com.example.server.persistence.entities.BaseEntity;

import java.util.List;

public interface iServicable<T extends BaseEntity> {
    T create(T entity);
    List<T> getAll();
    T getById(Long id);
    T update(Long id, T entity);
    Boolean delete(Long id);
}

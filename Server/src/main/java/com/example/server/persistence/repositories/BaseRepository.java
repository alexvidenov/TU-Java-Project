package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {
    BaseEntity GetBaseEntityById(Long id);
}

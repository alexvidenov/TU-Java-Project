package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity GetUserById(Long id);
    UserEntity GetUserByUsername(String username);
}

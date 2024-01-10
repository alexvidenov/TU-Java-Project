package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.id = ?1")
    UserEntity getUserById(Long id);

    @Query("select u from UserEntity u where u.username like %?1%")
    List<UserEntity> getUserByUsernameContaining(String username);
}

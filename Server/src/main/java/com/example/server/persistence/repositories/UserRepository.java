package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query("select u from UserEntity u where u.id = ?1")
    UserEntity getUserById(Long id);
    @Query("select u from UserEntity u where u.username like %?1%")
    List<UserEntity> getUserByUsernameContaining(String username);
    @Query("select u from UserEntity u")
    List<UserEntity> getAllUsers();
    @Modifying
    @Query("delete UserEntity u where u.id = ?1")
    Integer deleteUser(Long id);
}

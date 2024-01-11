package com.example.server.repositoriesTests;

import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveUser_shouldReturnSavedUser() {
        UserEntity testUser = new UserEntity();
        UserEntity savedUser = userRepository.save(testUser);

        assertEquals(entityManager.find(UserEntity.class, savedUser.id).id, testUser.id);
        assertEquals(entityManager.find(UserEntity.class, savedUser.id).getUsername(), testUser.getUsername());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        UserEntity testUser = new UserEntity();
        userRepository.save(testUser);

        UserEntity resultUser = userRepository.getUserById(testUser.id);

        assertEquals(entityManager.find(UserEntity.class, resultUser.id).id, testUser.id);
        assertEquals(entityManager.find(UserEntity.class, resultUser.id).getUsername(), testUser.getUsername());
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        UserEntity testUser1 = new UserEntity();
        UserEntity testUser2 = new UserEntity();
        UserEntity testUser3 = new UserEntity();

        List<UserEntity> testUsers = Arrays.asList(testUser1, testUser2, testUser3);

        userRepository.saveAll(testUsers);

        List<UserEntity> resultList = userRepository.getAllUsers();

        assertEquals(testUsers.size(), resultList.size());
    }

    @Test
    void deleteUser_shouldReturnTrueIfDeleted() {
        UserEntity testUser = new UserEntity();
        userRepository.save(testUser);

        var result = userRepository.deleteUser(testUser.id);

        assertEquals(1, result);
    }

    @Test
    void getUserByUsernameContaining_shouldReturnListOfUsers() {
        UserEntity testUser1 = new UserEntity();
        testUser1.setUsername("username1");
        UserEntity testUser2 = new UserEntity();
        testUser2.setUsername("username2");
        UserEntity testUser3 = new UserEntity();
        testUser3.setUsername("username3");

        List<UserEntity> testUsers = Arrays.asList(testUser1, testUser2, testUser3);

        userRepository.saveAll(testUsers);

        List<UserEntity> resultList = userRepository.getUserByUsernameContaining("user");

        assertEquals(testUsers.size(), resultList.size());
    }
}


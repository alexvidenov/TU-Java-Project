package com.example.server.controllerTests;

import com.example.server.network.controllers.UserController;
import com.example.server.network.dtos.UserDto;
import com.example.server.persistence.entities.UserEntity;
import com.example.server.services.BaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {

    @Mock
    private BaseService<UserEntity> userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser_shouldReturnCreatedUser() {
        UserEntity newUser = new UserEntity();
        Mockito.when(userService.create(Mockito.any(UserEntity.class))).thenReturn(newUser);

        ResponseEntity<UserDto> response = userController.create(userController.mapToDtoRepresentation(newUser));

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newUser, response.getBody());
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        List<UserEntity> users = new ArrayList<>();
        Mockito.when(userService.getAll()).thenReturn(users);

        ResponseEntity<List<UserDto>> response = userController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(users, response.getBody());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        Long userId = 1L;
        UserEntity existingUser = new UserEntity();
        Mockito.when(userService.getById(userId)).thenReturn(existingUser);

        ResponseEntity<UserDto> response = userController.getById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingUser, response.getBody());
    }

    @Test
    void getUserById_shouldReturnNotFoundIfUserDoesNotExist() {
        Long userId = 1L;
        Mockito.when(userService.getById(userId)).thenReturn(null);

        ResponseEntity<UserDto> response = userController.getById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateUser_shouldReturnUpdatedUserIfExists() {
        Long userId = 1L;
        UserEntity updatedUser = new UserEntity();
        Mockito.when(userService.update(userId, updatedUser)).thenReturn(updatedUser);

        ResponseEntity<UserDto> response = userController.update(userId, userController.mapToDtoRepresentation(updatedUser));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void updateUser_shouldReturnNotFoundIfUserDoesNotExist() {
        Long userId = 1L;
        UserEntity updatedUser = new UserEntity();
        Mockito.when(userService.update(userId, updatedUser)).thenReturn(null);

        ResponseEntity<UserDto> response = userController.update(userId, userController.mapToDtoRepresentation(updatedUser));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNoContentIfUserExists() {
        Long userId = 1L;
        Mockito.when(userService.delete(userId)).thenReturn(true);

        ResponseEntity<Void> response = userController.delete(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNotFoundIfUserDoesNotExist() {
        Long userId = 1L;
        Mockito.when(userService.delete(userId)).thenReturn(false);

        ResponseEntity<Void> response = userController.delete(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

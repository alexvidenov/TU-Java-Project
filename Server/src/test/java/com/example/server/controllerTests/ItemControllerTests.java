package com.example.server.controllerTests;

import com.example.server.controllers.ItemController;
import com.example.server.persistence.entities.ItemEntity;
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
class ItemControllerTests {

    @Mock
    private BaseService<ItemEntity> itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void createItem_shouldReturnCreatedItem() {
        ItemEntity newItem = new ItemEntity();
        Mockito.when(itemService.create(Mockito.any(ItemEntity.class))).thenReturn(newItem);

        ResponseEntity<ItemEntity> response = itemController.create(newItem);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newItem, response.getBody());
    }

    @Test
    void list_shouldReturnListOfItems() {
        List<ItemEntity> items = new ArrayList<>();
        Mockito.when(itemService.getAll()).thenReturn(items);

        ResponseEntity<List<ItemEntity>> response = itemController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(items, response.getBody());
    }

    @Test
    void getById_shouldReturnItemIfExists() {
        Long itemId = 1L;
        ItemEntity existingItem = new ItemEntity();
        Mockito.when(itemService.getById(itemId)).thenReturn(existingItem);

        ResponseEntity<ItemEntity> response = itemController.getById(itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingItem, response.getBody());
    }

    @Test
    void getById_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        Mockito.when(itemService.getById(itemId)).thenReturn(null);

        ResponseEntity<ItemEntity> response = itemController.getById(itemId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void update_shouldReturnUpdatedItemIfExists() {
        Long itemId = 1L;
        ItemEntity updatedItem = new ItemEntity();
        Mockito.when(itemService.update(itemId, updatedItem)).thenReturn(updatedItem);

        ResponseEntity<ItemEntity> response = itemController.update(itemId, updatedItem);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedItem, response.getBody());
    }

    @Test
    void update_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        ItemEntity updatedItem = new ItemEntity();
        Mockito.when(itemService.update(itemId, updatedItem)).thenReturn(null);

        ResponseEntity<ItemEntity> response = itemController.update(itemId, updatedItem);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void delete_shouldReturnNoContentIfItemExists() {
        Long itemId = 1L;
        Mockito.when(itemService.delete(itemId)).thenReturn(true);

        ResponseEntity<Void> response = itemController.delete(itemId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void delete_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        Mockito.when(itemService.delete(itemId)).thenReturn(false);

        ResponseEntity<Void> response = itemController.delete(itemId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

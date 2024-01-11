package com.example.server.controllerTests;

import com.example.server.controllers.ItemController;
import com.example.server.persistence.entities.ItemEntity;
import com.example.server.services.iServicable;
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
class ItemControllerTest {

    @Mock
    private iServicable<ItemEntity> itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void createItem_shouldReturnCreatedItem() {
        ItemEntity newItem = new ItemEntity();
        Mockito.when(itemService.create(Mockito.any(ItemEntity.class))).thenReturn(newItem);

        ResponseEntity<ItemEntity> response = itemController.createItem(newItem);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newItem, response.getBody());
    }

    @Test
    void getAllItems_shouldReturnListOfItems() {
        List<ItemEntity> items = new ArrayList<>();
        Mockito.when(itemService.getAll()).thenReturn(items);

        ResponseEntity<List<ItemEntity>> response = itemController.getAllItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(items, response.getBody());
    }

    @Test
    void getItemById_shouldReturnItemIfExists() {
        Long itemId = 1L;
        ItemEntity existingItem = new ItemEntity();
        Mockito.when(itemService.getById(itemId)).thenReturn(existingItem);

        ResponseEntity<ItemEntity> response = itemController.getItemById(itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingItem, response.getBody());
    }

    @Test
    void getItemById_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        Mockito.when(itemService.getById(itemId)).thenReturn(null);

        ResponseEntity<ItemEntity> response = itemController.getItemById(itemId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateItem_shouldReturnUpdatedItemIfExists() {
        Long itemId = 1L;
        ItemEntity updatedItem = new ItemEntity();
        Mockito.when(itemService.update(itemId, updatedItem)).thenReturn(updatedItem);

        ResponseEntity<ItemEntity> response = itemController.updateItem(itemId, updatedItem);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedItem, response.getBody());
    }

    @Test
    void updateItem_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        ItemEntity updatedItem = new ItemEntity();
        Mockito.when(itemService.update(itemId, updatedItem)).thenReturn(null);

        ResponseEntity<ItemEntity> response = itemController.updateItem(itemId, updatedItem);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteItem_shouldReturnNoContentIfItemExists() {
        Long itemId = 1L;
        Mockito.when(itemService.delete(itemId)).thenReturn(true);

        ResponseEntity<Void> response = itemController.deleteItem(itemId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteItem_shouldReturnNotFoundIfItemDoesNotExist() {
        Long itemId = 1L;
        Mockito.when(itemService.delete(itemId)).thenReturn(false);

        ResponseEntity<Void> response = itemController.deleteItem(itemId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

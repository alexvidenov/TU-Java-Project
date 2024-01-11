package com.example.server.controllerTests;

import com.example.server.controllers.ShopController;
import com.example.server.persistence.entities.ShopEntity;
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
class ShopControllerTest {

    @Mock
    private iServicable<ShopEntity> shopService;

    @InjectMocks
    private ShopController shopController;

    @Test
    void createShop_shouldReturnCreatedShop() {
        ShopEntity newShop = new ShopEntity();
        Mockito.when(shopService.create(Mockito.any(ShopEntity.class))).thenReturn(newShop);

        ResponseEntity<ShopEntity> response = shopController.createShop(newShop);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newShop, response.getBody());
    }

    @Test
    void getAllShops_shouldReturnListOfShops() {
        List<ShopEntity> shops = new ArrayList<>();
        Mockito.when(shopService.getAll()).thenReturn(shops);

        ResponseEntity<List<ShopEntity>> response = shopController.getAllShops();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shops, response.getBody());
    }

    @Test
    void getShopById_shouldReturnShopIfExists() {
        Long shopId = 1L;
        ShopEntity existingShop = new ShopEntity();
        Mockito.when(shopService.getById(shopId)).thenReturn(existingShop);

        ResponseEntity<ShopEntity> response = shopController.getShopById(shopId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingShop, response.getBody());
    }

    @Test
    void getShopById_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        Mockito.when(shopService.getById(shopId)).thenReturn(null);

        ResponseEntity<ShopEntity> response = shopController.getShopById(shopId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateShop_shouldReturnUpdatedShopIfExists() {
        Long shopId = 1L;
        ShopEntity updatedShop = new ShopEntity();
        Mockito.when(shopService.update(shopId, updatedShop)).thenReturn(updatedShop);

        ResponseEntity<ShopEntity> response = shopController.updateShop(shopId, updatedShop);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedShop, response.getBody());
    }

    @Test
    void updateShop_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        ShopEntity updatedShop = new ShopEntity();
        Mockito.when(shopService.update(shopId, updatedShop)).thenReturn(null);

        ResponseEntity<ShopEntity> response = shopController.updateShop(shopId, updatedShop);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteShop_shouldReturnNoContentIfShopExists() {
        Long shopId = 1L;
        Mockito.when(shopService.delete(shopId)).thenReturn(true);

        ResponseEntity<Void> response = shopController.deleteShop(shopId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteShop_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        Mockito.when(shopService.delete(shopId)).thenReturn(false);

        ResponseEntity<Void> response = shopController.deleteShop(shopId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

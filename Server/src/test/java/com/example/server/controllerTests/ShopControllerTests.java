package com.example.server.controllerTests;

import com.example.server.network.controllers.ShopController;
import com.example.server.network.dtos.ShopDto;
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
class ShopControllerTests {

    @Mock
    private BaseService<ShopDto> shopService;

    @InjectMocks
    private ShopController shopController;

    @Test
    void createShop_shouldReturnCreatedShop() {
        ShopDto newShop = new ShopDto();
        Mockito.when(shopService.create(Mockito.any(ShopDto.class))).thenReturn(newShop);

        ResponseEntity<ShopDto> response = shopController.create(newShop);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newShop, response.getBody());
    }

    @Test
    void getAllShops_shouldReturnListOfShops() {
        List<ShopDto> shops = new ArrayList<>();
        Mockito.when(shopService.getAll()).thenReturn(shops);

        ResponseEntity<List<ShopDto>> response = shopController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shops, response.getBody());
    }

    @Test
    void getShopById_shouldReturnShopIfExists() {
        Long shopId = 1L;
        ShopDto existingShop = new ShopDto();
        Mockito.when(shopService.getById(shopId)).thenReturn(existingShop);

        ResponseEntity<ShopDto> response = shopController.getById(shopId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingShop, response.getBody());
    }

    @Test
    void getShopById_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        Mockito.when(shopService.getById(shopId)).thenReturn(null);

        ResponseEntity<ShopDto> response = shopController.getById(shopId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateShop_shouldReturnUpdatedShopIfExists() {
        Long shopId = 1L;
        ShopDto updatedShop = new ShopDto();
        Mockito.when(shopService.update(shopId, updatedShop)).thenReturn(updatedShop);

        ResponseEntity<ShopDto> response = shopController.update(shopId, updatedShop);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedShop, response.getBody());
    }

    @Test
    void updateShop_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        ShopDto updatedShop = new ShopDto();
        Mockito.when(shopService.update(shopId, updatedShop)).thenReturn(null);

        ResponseEntity<ShopDto> response = shopController.update(shopId, updatedShop);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteShop_shouldReturnNoContentIfShopExists() {
        Long shopId = 1L;
        Mockito.when(shopService.delete(shopId)).thenReturn(true);

        ResponseEntity<Void> response = shopController.delete(shopId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteShop_shouldReturnNotFoundIfShopDoesNotExist() {
        Long shopId = 1L;
        Mockito.when(shopService.delete(shopId)).thenReturn(false);

        ResponseEntity<Void> response = shopController.delete(shopId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

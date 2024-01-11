package com.example.server.controllerTests;

import com.example.server.network.controllers.ShoppingCartController;
import com.example.server.network.dtos.ShoppingCartDto;
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
class ShoppingCartControllerTests {

    @Mock
    private BaseService<ShoppingCartDto> shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Test
    void createShoppingCart_shouldReturnCreatedShoppingCart() {
        ShoppingCartDto newShoppingCart = new ShoppingCartDto();
        Mockito.when(shoppingCartService.create(Mockito.any(ShoppingCartDto.class))).thenReturn(newShoppingCart);

        ResponseEntity<ShoppingCartDto> response = shoppingCartController.create(newShoppingCart);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newShoppingCart, response.getBody());
    }

    @Test
    void getAllShoppingCarts_shouldReturnListOfShoppingCarts() {
        List<ShoppingCartDto> shoppingCarts = new ArrayList<>();
        Mockito.when(shoppingCartService.getAll()).thenReturn(shoppingCarts);

        ResponseEntity<List<ShoppingCartDto>> response = shoppingCartController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shoppingCarts, response.getBody());
    }

    @Test
    void getShoppingCartById_shouldReturnShoppingCartIfExists() {
        Long cartId = 1L;
        ShoppingCartDto existingShoppingCart = new ShoppingCartDto();
        Mockito.when(shoppingCartService.getById(cartId)).thenReturn(existingShoppingCart);

        ResponseEntity<ShoppingCartDto> response = shoppingCartController.getById(cartId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(existingShoppingCart, response.getBody());
    }

    @Test
    void getShoppingCartById_shouldReturnNotFoundIfShoppingCartDoesNotExist() {
        Long cartId = 1L;
        Mockito.when(shoppingCartService.getById(cartId)).thenReturn(null);

        ResponseEntity<ShoppingCartDto> response = shoppingCartController.getById(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateShoppingCart_shouldReturnUpdatedShoppingCartIfExists() {
        Long cartId = 1L;
        ShoppingCartDto updatedShoppingCart = new ShoppingCartDto();
        Mockito.when(shoppingCartService.update(cartId, updatedShoppingCart)).thenReturn(updatedShoppingCart);

        ResponseEntity<ShoppingCartDto> response = shoppingCartController.update(cartId, updatedShoppingCart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedShoppingCart, response.getBody());
    }

    @Test
    void updateShoppingCart_shouldReturnNotFoundIfShoppingCartDoesNotExist() {
        Long cartId = 1L;
        ShoppingCartDto updatedShoppingCart = new ShoppingCartDto();
        Mockito.when(shoppingCartService.update(cartId, updatedShoppingCart)).thenReturn(null);

        ResponseEntity<ShoppingCartDto> response = shoppingCartController.update(cartId, updatedShoppingCart);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteShoppingCart_shouldReturnNoContentIfShoppingCartExists() {
        Long cartId = 1L;
        Mockito.when(shoppingCartService.delete(cartId)).thenReturn(true);

        ResponseEntity<Void> response = shoppingCartController.delete(cartId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteShoppingCart_shouldReturnNotFoundIfShoppingCartDoesNotExist() {
        Long cartId = 1L;
        Mockito.when(shoppingCartService.delete(cartId)).thenReturn(false);

        ResponseEntity<Void> response = shoppingCartController.delete(cartId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

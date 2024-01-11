package com.example.server.controllers;

import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.services.iServicable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private iServicable<ShoppingCartEntity> shoppingCartService;

    // Create a new shopping cart
    @PostMapping
    public ResponseEntity<ShoppingCartEntity> createShoppingCart(@RequestBody ShoppingCartEntity shoppingCart) {
        ShoppingCartEntity createdShoppingCart = shoppingCartService.create(shoppingCart);
        return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
    }

    // Get all shopping carts
    @GetMapping
    public ResponseEntity<List<ShoppingCartEntity>> getAllShoppingCarts() {
        List<ShoppingCartEntity> shoppingCarts = shoppingCartService.getAll();
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    // Get shopping cart by ID
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCartEntity> getShoppingCartById(@PathVariable Long cartId) {
        ShoppingCartEntity shoppingCart = shoppingCartService.getById(cartId);
        if (shoppingCart != null) {
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update shopping cart by ID
    @PutMapping("/{cartId}")
    public ResponseEntity<ShoppingCartEntity> updateShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartEntity updatedShoppingCart) {
        ShoppingCartEntity shoppingCart = shoppingCartService.update(cartId, updatedShoppingCart);
        if (shoppingCart != null) {
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete shopping cart by ID
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Long cartId) {
        Boolean deleteResult = shoppingCartService.delete(cartId);
        if (deleteResult) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


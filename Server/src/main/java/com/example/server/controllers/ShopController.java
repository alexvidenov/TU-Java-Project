package com.example.server.controllers;

import com.example.server.persistence.entities.ShopEntity;
import com.example.server.services.iServicable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private iServicable<ShopEntity> shopService;

    // Create a new shop
    @PostMapping
    public ResponseEntity<ShopEntity> createShop(@RequestBody ShopEntity shop) {
        ShopEntity createdShop = shopService.create(shop);
        return new ResponseEntity<>(createdShop, HttpStatus.CREATED);
    }

    // Get all shops
    @GetMapping
    public ResponseEntity<List<ShopEntity>> getAllShops() {
        List<ShopEntity> shops = shopService.getAll();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    // Get shop by ID
    @GetMapping("/{shopId}")
    public ResponseEntity<ShopEntity> getShopById(@PathVariable Long shopId) {
        ShopEntity shop = shopService.getById(shopId);
        if (shop != null) {
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update shop by ID
    @PutMapping("/{shopId}")
    public ResponseEntity<ShopEntity> updateShop(@PathVariable Long shopId, @RequestBody ShopEntity updatedShop) {
        ShopEntity shop = shopService.update(shopId, updatedShop);
        if (shop != null) {
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete shop by ID
    @DeleteMapping("/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long shopId) {
        Boolean deleteResult = shopService.delete(shopId);
        if (deleteResult) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


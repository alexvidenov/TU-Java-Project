package com.example.server.controllers;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.services.iServicable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private iServicable<ItemEntity> itemService;

    // Create a new item
    @PostMapping
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) {
        ItemEntity createdItem = itemService.create(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItems() {
        List<ItemEntity> items = itemService.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Get item by ID
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable Long itemId) {
        ItemEntity item = itemService.getById(itemId);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update item by ID
    @PutMapping("/{itemId}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable Long itemId, @RequestBody ItemEntity updatedItem) {
        ItemEntity item = itemService.update(itemId, updatedItem);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete item by ID
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        Boolean deleteResult = itemService.delete(itemId);
        if (deleteResult) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


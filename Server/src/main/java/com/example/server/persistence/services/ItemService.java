package com.example.server.persistence.services;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> getAllItemsFromShopById(Long shopId){
        return itemRepository.getAllItemsFromShopById(shopId);
    }

    public List<ItemEntity> getAllItemsFromShop(ShopEntity shop){
        return itemRepository.getAllItemsFromShop(shop);
    }

    public ItemEntity getItemById(Long itemId){
        return itemRepository.getItemById(itemId);
    }

    public List<ItemEntity> getItemsWithName(String name){
        return itemRepository.getItemsWithNameContaining(name);
    }

    public List<ItemEntity> getItemsWithDescription(String description){
        return itemRepository.getItemsWithDescription(description);
    }
}

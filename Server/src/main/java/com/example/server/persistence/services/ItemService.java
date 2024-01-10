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

    public List<ItemEntity> GetAllItemsFromShopById(Long shopId){
        return itemRepository.GetAllItemsFromShopById(shopId);
    }

    public List<ItemEntity> GetAllItemsFromShop(ShopEntity shop){
        return itemRepository.GetAllItemsFromShop(shop);
    }

    public ItemEntity GetItemById(Long itemId){
        return itemRepository.GetItemById(itemId);
    }

    public List<ItemEntity> GetItemsWithName(String name){
        return itemRepository.GetItemsWithName(name);
    }

    public List<ItemEntity> GetItemsWithDescription(String description){
        return itemRepository.GetItemsWithDescription(description);
    }
}

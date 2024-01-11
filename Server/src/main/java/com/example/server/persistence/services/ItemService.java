package com.example.server.persistence.services;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ItemRepository;
import com.example.server.services.iServicable;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements iServicable<ItemEntity> {
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

    public List<ItemEntity> getItemsWithName(String name){
        return itemRepository.getItemsWithNameContaining(name);
    }

    public List<ItemEntity> getItemsWithDescription(String description){
        return itemRepository.getItemsWithDescription(description);
    }

    @Override
    public ItemEntity create(ItemEntity entity) {
        return itemRepository.save(entity);
    }

    @Override
    public List<ItemEntity> getAll() {
        return itemRepository.getAllItems();
    }

    @Override
    public ItemEntity getById(Long id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public ItemEntity update(Long id, ItemEntity entity) {
        return itemRepository.updateItem(id, entity);
    }

    @Override
    public Boolean delete(Long id) {
        return itemRepository.deleteItem(id);
    }
}

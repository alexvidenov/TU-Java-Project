package com.example.server.persistence.services;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ItemRepository;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements BaseService<ItemEntity> {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> listFromShopById(Long shopId){
        return itemRepository.listFromShopById(shopId);
    }

    public List<ItemEntity> listFromShop(ShopEntity shop){
        return itemRepository.listFromShop(shop);
    }

    public List<ItemEntity> getItemsWithName(String name){
        return itemRepository.getItemsWithNameContaining(name);
    }

    public List<ItemEntity> getItemsWithDescription(String description){
        return itemRepository.getItemsWithDescriptionContaining(description);
    }

    @Override
    public ItemEntity create(ItemEntity entity) {
        return itemRepository.save(entity);
    }

    @Override
    public List<ItemEntity> getAll() {
        return itemRepository.list();
    }

    @Override
    public ItemEntity getById(Long id) {
        return itemRepository.getById(id);
    }

    @Override
    public ItemEntity update(Long id, ItemEntity entity) {
        if(entity.id.equals(id)){
            itemRepository.save(entity);
        }
        else{
            ItemEntity targetEntity = getById(id);
            targetEntity.setName(entity.getName());
            targetEntity.setDescription(entity.getDescription());
            targetEntity.setShop(entity.getShop());
            itemRepository.save(targetEntity);
        }
        return getById(id);
    }

    @Override
    public Boolean delete(Long id) {
        return itemRepository.delete(id) == 1;
    }
}

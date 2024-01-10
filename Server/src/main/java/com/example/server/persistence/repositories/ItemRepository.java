package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> GetAllItemsFromShopById(Long shopId);
    List<ItemEntity> GetAllItemsFromShop(ShopEntity shop);
    ItemEntity GetItemById(Long itemId);
    List<ItemEntity> GetItemsWithName(String name);
    List<ItemEntity> GetItemsWithDescription(String description);

}

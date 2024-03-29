package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query("select i from ItemEntity i where i.shop.id = ?1")
    List<ItemEntity> listFromShopById(Long shopId);
    @Query("select i from ItemEntity i where i.shop = ?1")
    List<ItemEntity> listFromShop(ShopEntity shop);
    @Query("select i from ItemEntity i where i.id = ?1")
    ItemEntity getById(Long itemId);
    @Query("select i from ItemEntity i")
    List<ItemEntity> list();
    @Query("select i from ItemEntity i where i.name like %?1%")
    List<ItemEntity> getItemsWithNameContaining(String name);
    @Query("select i from ItemEntity i where i.description like %?1%")
    List<ItemEntity> getItemsWithDescriptionContaining(String description);
    @Modifying
    @Query("delete ItemEntity i where i.id = ?1")
    Integer delete(Long id);
}

package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    @Query("select s from ShopEntity s where s.id = ?1")
    ShopEntity getShopById(Long shopId);
    @Query("select s from ShopEntity s where s.name like %?1%")
    List<ShopEntity> getShopsByNameContaining(String name);
    @Query("select s from ShopEntity s where s.description like %?1%")
    List<ShopEntity> getShopsByDescriptionContaining(String description);
    @Query("select i.shop from ItemEntity i where i.id = ?1")
    ShopEntity getShopByItemId(Long itemId);
    @Query("select s from ShopEntity s")
    List<ShopEntity> getAllShops();
    @Modifying
    @Query("delete ShopEntity s where s.id = ?1")
    Integer deleteShop(Long id);
}

package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    ShopEntity GetShopById(Long shopId);
    List<ShopEntity> GetShopsByName(String name);
    List<ShopEntity> GetShopsByDescription(String description);
    ShopEntity GetShopByItemId(Long itemId);
}

package com.example.server.persistence.services;

import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    public final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ShopEntity getShopById(Long shopId){
        return shopRepository.getShopById(shopId);
    }

    public List<ShopEntity> getShopsByName(String name){
        return shopRepository.getShopsByNameContaining(name);
    }

    public List<ShopEntity> getShopsByDescription(String description){
        return shopRepository.getShopsByDescriptionContaining(description);
    }

    public ShopEntity getShopByItemId(Long itemId){
        return shopRepository.getShopByItemId(itemId);
    }
}

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

    public ShopEntity GetShopById(Long shopId){
        return shopRepository.GetShopById(shopId);
    }

    public List<ShopEntity> GetShopsByName(String name){
        return shopRepository.GetShopsByName(name);
    }

    public List<ShopEntity> GetShopsByDescription(String description){
        return shopRepository.GetShopsByDescription(description);
    }

    public ShopEntity GetShopByItemId(Long itemId){
        return shopRepository.GetShopByItemId(itemId);
    }
}

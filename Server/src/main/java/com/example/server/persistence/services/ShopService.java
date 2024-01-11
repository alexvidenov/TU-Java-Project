package com.example.server.persistence.services;

import com.example.server.persistence.entities.ShopEntity;
import com.example.server.persistence.repositories.ShopRepository;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements BaseService<ShopEntity> {
    public final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
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

    @Override
    public ShopEntity create(ShopEntity entity) {
        return shopRepository.save(entity);
    }

    @Override
    public List<ShopEntity> getAll() {
        return shopRepository.getAllShops();
    }

    @Override
    public ShopEntity getById(Long id) {
        return shopRepository.getShopById(id);
    }

    @Override
    public ShopEntity update(Long id, ShopEntity entity) {
        return shopRepository.updateShopEntity(id, entity);
    }

    @Override
    public Boolean delete(Long id) {
        return shopRepository.deleteShop(id);
    }
}

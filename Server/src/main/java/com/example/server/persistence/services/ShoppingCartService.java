package com.example.server.persistence.services;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.ShoppingCartRepository;
import com.example.server.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements BaseService<ShoppingCartEntity> {
    public final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCartEntity getShoppingCartByUser(UserEntity user){
        return shoppingCartRepository.getShoppingCartByUser(user);
    }
    public ShoppingCartEntity getShoppingCartByUserId(Long userId){
        return shoppingCartRepository.getShoppingCartByUserId(userId);
    }
    public List<ItemEntity> getItemsFromShoppingCart(ShoppingCartEntity shoppingCart){
        return shoppingCartRepository.getItemsFromShoppingCart(shoppingCart);
    }
    public List<ItemEntity> getItemsFromUsersShoppingCart(UserEntity user){
        return shoppingCartRepository.getItemsFromUsersShoppingCart(user);
    }
    public List<ItemEntity> getItemsFromUsersShoppingCartByUserId(Long userId){
        return shoppingCartRepository.getItemsFromUsersShoppingCartByUserId(userId);
    }

    @Override
    public ShoppingCartEntity create(ShoppingCartEntity entity) {
        return shoppingCartRepository.save(entity);
    }

    @Override
    public List<ShoppingCartEntity> getAll() {
        return shoppingCartRepository.getAllShoppingCarts();
    }

    @Override
    public ShoppingCartEntity getById(Long id) {
        return shoppingCartRepository.getShoppingCartById(id);
    }

    @Override
    public ShoppingCartEntity update(Long id, ShoppingCartEntity entity) {
        return shoppingCartRepository.updateShoppingCart(id, entity);
    }

    @Override
    public Boolean delete(Long id) {
        return shoppingCartRepository.deleteShoppingCart(id);
    }
}

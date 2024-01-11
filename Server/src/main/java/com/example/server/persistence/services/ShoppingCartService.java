package com.example.server.persistence.services;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.persistence.entities.UserEntity;
import com.example.server.persistence.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
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
}

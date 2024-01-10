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

    public ShoppingCartEntity GetShoppingCartByUser(UserEntity user){
        return shoppingCartRepository.GetShoppingCartByUser(user);
    }
    public ShoppingCartEntity GetShoppingCartByUserId(Long userId){
        return shoppingCartRepository.GetShoppingCartByUserId(userId);
    }
    public List<ItemEntity> GetItemsFromShoppingCart(ShoppingCartEntity shoppingCart){
        return shoppingCartRepository.GetItemsFromShoppingCart(shoppingCart);
    }
    public List<ItemEntity> GetItemsFromUsersShoppingCart(UserEntity user){
        return shoppingCartRepository.GetItemsFromUsersShoppingCart(user);
    }
    public List<ItemEntity> GetItemsFromUsersShoppingCartByUserId(Long userId){
        return shoppingCartRepository.GetItemsFromUsersShoppingCartByUserId(userId);
    }
}

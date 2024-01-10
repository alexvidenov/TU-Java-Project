package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    ShoppingCartEntity GetShoppingCartByUser(UserEntity user);
    ShoppingCartEntity GetShoppingCartByUserId(Long userId);
    List<ItemEntity> GetItemsFromShoppingCart(ShoppingCartEntity shoppingCart);
    List<ItemEntity> GetItemsFromUsersShoppingCart(UserEntity user);
    List<ItemEntity> GetItemsFromUsersShoppingCartByUserId(Long userId);
}

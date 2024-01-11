package com.example.server.persistence.repositories;

import com.example.server.persistence.entities.ItemEntity;
import com.example.server.persistence.entities.ShoppingCartEntity;
import com.example.server.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    @Query("select sc from ShoppingCartEntity sc where sc.user = ?1")
    ShoppingCartEntity getShoppingCartByUser(UserEntity user);
    @Query("select sc from ShoppingCartEntity sc where sc.user.id = ?1")
    ShoppingCartEntity getShoppingCartByUserId(Long userId);
    @Query("select sc from ShoppingCartEntity sc where sc.id = ?1")
    ShoppingCartEntity getShoppingCartById(Long id);
    @Query("select sc from ShoppingCartEntity sc")
    List<ShoppingCartEntity> getAllShoppingCarts();
    @Query("select sc.items from ShoppingCartEntity sc where sc = ?1")
    List<ItemEntity> getItemsFromShoppingCart(ShoppingCartEntity shoppingCart);
    @Query("select sc.items from ShoppingCartEntity sc where sc.user = ?1")
    List<ItemEntity> getItemsFromUsersShoppingCart(UserEntity user);
    @Query("select sc.items from ShoppingCartEntity sc where sc.user.id = ?1")
    List<ItemEntity> getItemsFromUsersShoppingCartByUserId(Long userId);
    @Query("update ShoppingCartEntity sc set sc = ?2 where sc.id = ?1")
    ShoppingCartEntity updateShoppingCart(Long id, ShoppingCartEntity shoppingCart);
    @Query("delete ShoppingCartEntity sc where sc.id = ?1")
    Boolean deleteShoppingCart(Long id);
}

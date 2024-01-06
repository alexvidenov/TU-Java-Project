package com.example.persistence.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "shopping_cart_items",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    Set<ItemEntity> items;
}

package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity {
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToMany(mappedBy = "items")
    Set<ShoppingCartEntity> shoppingCarts;
}

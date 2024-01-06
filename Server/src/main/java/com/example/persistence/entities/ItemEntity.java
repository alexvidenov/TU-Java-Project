package com.example.persistence.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToMany(mappedBy = "item_id")
    Set<ShoppingCartEntity> shoppingCarts;
}

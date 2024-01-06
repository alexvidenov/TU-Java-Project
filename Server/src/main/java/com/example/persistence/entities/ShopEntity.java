package com.example.persistence.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "shops")
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "shop_id")
    private Set<ItemEntity> items;
}
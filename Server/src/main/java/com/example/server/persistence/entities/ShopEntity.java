package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "shops")
public class ShopEntity extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "shop")
    private Set<ItemEntity> items;
}
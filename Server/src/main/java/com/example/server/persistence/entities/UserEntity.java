package com.example.server.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    @OneToOne(mappedBy = "user")
    private ShoppingCartEntity shoppingCard;
}

package com.example.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    @OneToOne(mappedBy = "user_id")
    private ShoppingCartEntity shoppingCard;
}

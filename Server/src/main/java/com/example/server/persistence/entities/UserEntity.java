package com.example.server.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    @OneToOne(mappedBy = "user")
    private ShoppingCartEntity shoppingCard;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ShoppingCartEntity getShoppingCard() {
        return shoppingCard;
    }

    public void setShoppingCard(ShoppingCartEntity shoppingCard) {
        this.shoppingCard = shoppingCard;
    }
}

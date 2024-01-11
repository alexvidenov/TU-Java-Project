package com.example.server.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCartEntity shoppingCart;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCard) {
        this.shoppingCart = shoppingCard;
    }
}

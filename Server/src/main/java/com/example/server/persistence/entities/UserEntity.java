package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getShoppingCart(), that.getShoppingCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getShoppingCart());
    }
}

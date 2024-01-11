package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.Objects;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemEntity that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getShop(), that.getShop()) && Objects.equals(shoppingCarts, that.shoppingCarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getShop(), shoppingCarts);
    }
}

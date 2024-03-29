package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "shopping_cart_items",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    List<ItemEntity> items;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void addItem(ItemEntity item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartEntity that)) return false;
        return Objects.equals(getUser(), that.getUser()) && Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getItems());
    }
}

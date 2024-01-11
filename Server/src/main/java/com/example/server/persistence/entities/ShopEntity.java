package com.example.server.persistence.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shops")
public class ShopEntity extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "shop")
    private Set<ItemEntity> items;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopEntity that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), items);
    }
}
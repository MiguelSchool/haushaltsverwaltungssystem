package com.example.haushaltsverwaltungssystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingList extends BaseEntity {

    private String name;

    private Double total;

    @ManyToMany
    @JoinTable(
            name="product_shopping_list",
            joinColumns = @JoinColumn(name="shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product>products;

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

    public Set<Product> getProducts() {
        return products;
    }
}

package com.example.haushaltsverwaltungssystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Product extends BaseEntity {

    private String name;
    private Boolean isFreezable;
    private String brand;
    private Integer amountPieces;
    private Double total;
    private Boolean isWeightArticle;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToMany
    @JoinTable(
            name = "product_shopping_list",
            joinColumns = @JoinColumn(name = "shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ShoppingList> shoppinglists;

    public String getName() {
        return name;
    }

    public Boolean getFreezable() {
        return isFreezable;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getAmountPieces() {
        return amountPieces;
    }

    public Double getTotal() {
        return total;
    }

    public Boolean getWeightArticle() {
        return isWeightArticle;
    }

    public Double getWeight() {
        return weight;
    }


    public Set<ShoppingList> getShoppinglists() {
        return shoppinglists;
    }
}

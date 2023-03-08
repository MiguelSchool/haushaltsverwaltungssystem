package com.example.haushaltsverwaltungssystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Product extends BaseEntity {

    private String name;
    private Boolean isFreezable;
    private String brand;
    private Double amountPieces;
    private Double price;
    private Boolean isWeightArticle;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "product_shopping_list",
            joinColumns = @JoinColumn(name = "shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ShoppingList> shoppingLists = new HashSet<>();

    @ManyToOne
    private SocialGroup socialGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        if (!name.equals(product.name)) return false;
        if (!Objects.equals(isFreezable, product.isFreezable)) return false;
        if (!Objects.equals(brand, product.brand)) return false;
        if (!Objects.equals(price, product.price)) return false;
        if (!Objects.equals(isWeightArticle, product.isWeightArticle))
            return false;
        return Objects.equals(weight, product.weight);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (isFreezable != null ? isFreezable.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isWeightArticle != null ? isWeightArticle.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}

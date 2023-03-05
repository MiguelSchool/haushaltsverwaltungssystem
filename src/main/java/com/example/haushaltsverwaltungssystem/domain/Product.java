package com.example.haushaltsverwaltungssystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    private Integer amountPieces;
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

}

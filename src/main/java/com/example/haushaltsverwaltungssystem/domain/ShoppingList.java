package com.example.haushaltsverwaltungssystem.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class ShoppingList extends BaseEntity {

    private String name;
    private Boolean isClosed;
    private BigDecimal totalCoasts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="product_shopping_list",
            joinColumns = @JoinColumn(name="shoppinglist_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product>products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "social_group_object_id", nullable = false)
    private SocialGroup socialGroup;

}

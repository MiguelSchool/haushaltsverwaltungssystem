package com.example.haushaltsverwaltungssystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class SocialGroup extends BaseEntity {
    private String name;
    private String shareCode;
    private String street;
    private String streetNumber;
    private String city;

    @OneToMany(mappedBy="socialGroup")
    private Set<ShoppingList> shoppingLists = new HashSet<>();
}

package com.example.haushaltsverwaltungssystem.domain;

import com.example.haushaltsverwaltungssystem.core.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "user_socialGroups_list",
            joinColumns = @JoinColumn(name = "socialGroup_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User>users;
    @ManyToOne
    @JoinColumn(name = "adress_object_id")
    private Adress adress;

    @OneToMany(mappedBy="socialGroup")
    private Set<ShoppingList> shoppingLists = new HashSet<>();
}

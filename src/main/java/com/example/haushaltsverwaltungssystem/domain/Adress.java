package com.example.haushaltsverwaltungssystem.domain;

import com.example.haushaltsverwaltungssystem.core.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Adress extends BaseEntity {
    private String street;
    private String streetNumber;
    private String zCode;
    private String city;

    @OneToMany
    private List<SocialGroup> socialGroups = new ArrayList<>();

    @OneToMany
    private Set<User> user = new HashSet<>();
}

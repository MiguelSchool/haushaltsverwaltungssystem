package com.example.haushaltsverwaltungssystem.core.domain;


import com.example.haushaltsverwaltungssystem.domain.Address;
import com.example.haushaltsverwaltungssystem.domain.BaseEntity;
import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Setter
@Getter
@Table(name = "user_entity")
public class User extends BaseEntity implements UserDetails {

    @NonNull
    @Column(name = "firstname")
    private String firstName;

    @NonNull
    @Column(name = "lastname")
    private String lastName;

    @NonNull
    @Column(name = "email")
    private String email;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "adress_object_id")
    private Address address;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_socialGroups_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "socialGroup_id")
    )
    List<SocialGroup> socialGroups = new ArrayList<>();

    private File image;

    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getProfileUsername() {
        return username;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

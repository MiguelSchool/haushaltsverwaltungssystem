package com.example.haushaltsverwaltungssystem.core.repository;

import com.example.haushaltsverwaltungssystem.core.domain.User;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
    }

    default User findUserByIdOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(()->new NotFoundException("User with email: " + email + " not found"));
    }

    Optional<User> findUserByEmail(String email);

}

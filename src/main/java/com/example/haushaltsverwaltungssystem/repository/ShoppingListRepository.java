package com.example.haushaltsverwaltungssystem.repository;

import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> { }

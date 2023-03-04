package com.example.haushaltsverwaltungssystem.service;

import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingListServiceImpl {

    ShoppingListRepository shoppingListRepository;
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public Optional<ShoppingList>saveShoppingList(ShoppingList shoppingList) {
        return Optional.of(shoppingListRepository.save(shoppingList));
    }
    public Optional<Set<ShoppingList>> getAllShoppingLists() {
        return Optional.of(new HashSet<>(shoppingListRepository.findAll()));
    }

    public Optional<ShoppingList> getOneShoppingList(Long id) {
        return (shoppingListRepository.findById(id));
    }
}

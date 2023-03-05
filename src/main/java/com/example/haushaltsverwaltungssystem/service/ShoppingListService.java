package com.example.haushaltsverwaltungssystem.service;

import com.example.haushaltsverwaltungssystem.domain.Product;
import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.repository.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingList saveShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList getOneShoppingList(Long id) {
        return shoppingListRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    public ShoppingList addProduct(Product product, Long id) {
        ShoppingList selectedShoppingList = this.getOneShoppingList(id);
        selectedShoppingList.getProducts().add(product);
        return saveShoppingList(selectedShoppingList);
    }
}

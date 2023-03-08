package com.example.haushaltsverwaltungssystem.service;

import com.example.haushaltsverwaltungssystem.domain.Product;
import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.repository.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        return shoppingListRepository.findById(id).orElseThrow(() -> new NotFoundException("Object not found"));
    }

    public void deleteShoppingList(Long id) {
        shoppingListRepository.deleteById(id);
    }

    public ShoppingList addProduct(Product product, Long id) {
        ShoppingList selectedShoppingList = this.getOneShoppingList(id);
        if (selectedShoppingList.getProducts().isEmpty()) {
            selectedShoppingList.getProducts().add(product);
        } else {
            for (Product actualProduct: selectedShoppingList.getProducts()) {
                if (actualProduct.equals(product)) {
                    Double updatedAmountPieces = actualProduct.getAmountPieces() + product.getAmountPieces();
                    actualProduct.setAmountPieces(updatedAmountPieces);
                    selectedShoppingList.getProducts().remove(actualProduct);
                    selectedShoppingList.getProducts().add(actualProduct);
                    return saveShoppingList(selectedShoppingList);
                }
            }
            selectedShoppingList.getProducts().add(product);

        }
        return saveShoppingList(selectedShoppingList);
    }

    public void deleteProduct(Long id, Product ... productsToDelete) {
        ShoppingList selectedShoppingList = this.getOneShoppingList(id);
        Arrays.asList(productsToDelete).forEach(selectedShoppingList.getProducts()::remove);
        this.saveShoppingList(selectedShoppingList);
    }

}

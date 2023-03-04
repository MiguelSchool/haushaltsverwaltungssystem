package com.example.haushaltsverwaltungssystem.controller;

import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.exception.ResourceNotFoundException;
import com.example.haushaltsverwaltungssystem.service.ShoppingListServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ShoppingListController {

    ShoppingListServiceImpl shoppingListService;

    public ShoppingListController(ShoppingListServiceImpl shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/shoppinglists/{id}")
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<ShoppingList> getSingleShoppingList(@PathVariable Long id) {

        return ResponseEntity.ok(
                shoppingListService.getOneShoppingList(id).orElseThrow(() -> {
                    throw new NotFoundException("ShoppingList not Found !");
                })
        );
    }

    @GetMapping("/shoppinglists")
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<Set<ShoppingList>> getAllShoppingListsForSocialGroupById() {
        //TODO: SocialGroup get implements later; GetAllShoppingList by social group id
        return ResponseEntity.of(shoppingListService.getAllShoppingLists());
    }

    @PostMapping("/shoppinglists")
    @ResponseStatus( HttpStatus.CREATED )
    public ResponseEntity<ShoppingList> createShoppingListBySocialGroup(@RequestBody ShoppingList shoppingList) {
        //TODO: SocialGroup get implements later; GetAllShoppingList by social group id
        if (shoppingList == null) throw new ResourceNotFoundException("shoppingList is not initialized");

        return ResponseEntity.ok(
                shoppingListService.saveShoppingList(shoppingList).orElseThrow(() -> {
                    throw new ResourceNotFoundException("shoppingList is not initialized");
                }));
    }
}

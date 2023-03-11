package com.example.haushaltsverwaltungssystem.controller;

import com.example.haushaltsverwaltungssystem.domain.Product;
import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequestMapping("/shoppinglists")
@RestController()
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShoppingList> getSingleShoppingList(@RequestBody SocialGroup socialGroup, @PathVariable Long id) {
        return ResponseEntity.ok(socialGroup.getShoppingLists().stream()
                .filter(shoppingList -> shoppingList.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NotFoundException("object not found")));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ShoppingList>> getAllShoppingListsForSocialGroupById(@RequestBody SocialGroup socialGroup) {
        return ResponseEntity.ok(socialGroup.getShoppingLists().stream().toList());
    }

    @PostMapping("{id}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ShoppingList> addProductInShoppingList(@RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(shoppingListService.addProduct(product, id));
    }

    @DeleteMapping("/product/{id}")
    public void removeProductInShoppingList(@PathVariable Long id, @RequestBody Product product) {
        shoppingListService.deleteProduct(id, product);
    }

    @DeleteMapping("{id}")
    public void removeShoppingList(@PathVariable Long id) {
        shoppingListService.deleteShoppingList(id);
    }
}

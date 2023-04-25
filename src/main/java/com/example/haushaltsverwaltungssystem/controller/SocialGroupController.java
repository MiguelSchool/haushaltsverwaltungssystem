package com.example.haushaltsverwaltungssystem.controller;


import com.example.haushaltsverwaltungssystem.core.domain.User;
import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.example.haushaltsverwaltungssystem.service.SocialGroupService;
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
import java.util.Set;

@RequestMapping("/socialGroup")
@RestController()
@RequiredArgsConstructor
public class SocialGroupController {

    private final SocialGroupService socialGroupService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SocialGroup> getSingleShoppingList(@PathVariable Long id) {
        return ResponseEntity.ok(socialGroupService.getSingleSocialgroup(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SocialGroup>> getAllShoppingListsForSocialGroupById() {
        return ResponseEntity.ok(socialGroupService.getAllSocialGroups());
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<User>> getAllUsersFromSocialGroup(@PathVariable Long id) {
        return ResponseEntity.ok(socialGroupService.getAllUsersFromSingleSocialGroup(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SocialGroup> createShoppingListBySocialGroup(@RequestBody SocialGroup socialGroup) {
        SocialGroup socialGroupCreated = socialGroupService.saveSocialGroup(socialGroup);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(socialGroupCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteSocialGroup(@PathVariable Long id) {
        return ResponseEntity.ok(socialGroupService.deleteSocialGroup(id));
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SocialGroup> createShoppingListBySocialGroup(@RequestBody ShoppingList shoppingList, @PathVariable Long id) {
        SocialGroup shoppingListCreated = socialGroupService.saveShoppingList(id,shoppingList);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(shoppingListCreated);
    }

}

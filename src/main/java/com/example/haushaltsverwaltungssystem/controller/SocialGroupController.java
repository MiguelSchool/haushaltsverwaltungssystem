package com.example.haushaltsverwaltungssystem.controller;


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

import java.net.URI;
import java.util.List;

@RequestMapping("/socialGroup")
@RestController()
@RequiredArgsConstructor
public class SocialGroupController {

    private SocialGroupService socialGroupService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SocialGroup> createShoppingListBySocialGroup(@RequestBody SocialGroup socialGroup) {
        SocialGroup socialGroupCreated = socialGroupService.saveSocialGroup(socialGroup);
        return ResponseEntity.created(URI.create("#"))
                .body(socialGroupCreated);
        //TODO: return created URI
    }

    @DeleteMapping("{id}")
    public void deleteSocialGroup(@PathVariable Long id) {
        socialGroupService.deleteSocialGroup(id);
    }
}

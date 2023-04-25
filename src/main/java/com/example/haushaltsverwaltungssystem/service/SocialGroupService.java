package com.example.haushaltsverwaltungssystem.service;

import com.example.haushaltsverwaltungssystem.core.domain.User;
import com.example.haushaltsverwaltungssystem.domain.ShoppingList;
import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.repository.SocialGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SocialGroupService {

    private final SocialGroupRepository socialGroupRepository;

    public SocialGroup saveSocialGroup(SocialGroup socialGroup) {
        return socialGroupRepository.save(socialGroup);
    }

    public SocialGroup saveShoppingList(Long id, ShoppingList shoppingList) {
        SocialGroup selectedSocialGroup = socialGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("Object not found"));
        selectedSocialGroup.getShoppingLists().add(shoppingList);
        return socialGroupRepository.save(selectedSocialGroup);
    }
    public List<SocialGroup> getAllSocialGroups() {
        return socialGroupRepository.findAll();
    }

    public SocialGroup getSingleSocialgroup(Long id) {
        return socialGroupRepository.findByIdOrElseThrow(id);
    }

    public Boolean deleteSocialGroup(Long id) {
        socialGroupRepository.deleteById(id);
        return socialGroupRepository.findById(id).isPresent();
    }

    public Set<User> getAllUsersFromSingleSocialGroup(Long id) {
        SocialGroup selectedSocialGroup = socialGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Object not found"));
        return selectedSocialGroup.getUsers();
    }
}

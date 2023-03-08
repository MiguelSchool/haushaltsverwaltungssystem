package com.example.haushaltsverwaltungssystem.service;

import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.example.haushaltsverwaltungssystem.exception.NotFoundException;
import com.example.haushaltsverwaltungssystem.repository.SocialGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialGroupService {

    private final SocialGroupRepository socialGroupRepository;

    public SocialGroup saveSocialGroup(SocialGroup socialGroup) {
        return socialGroupRepository.save(socialGroup);
    }

    public List<SocialGroup> getAllSocialGroups() {
        return socialGroupRepository.findAll();
    }

    public SocialGroup getSingleSocialgroup(Long id) {
        return socialGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("Object not found"));
    }

    public void deleteSocialGroup(Long id) {
        socialGroupRepository.deleteById(id);
    }
}

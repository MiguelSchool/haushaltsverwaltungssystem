package com.example.haushaltsverwaltungssystem.repository;

import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import com.example.haushaltsverwaltungssystem.exception.SocialGroupNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialGroupRepository extends JpaRepository<SocialGroup, Long> {
    default SocialGroup findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new SocialGroupNotFoundException(id));
    }
}

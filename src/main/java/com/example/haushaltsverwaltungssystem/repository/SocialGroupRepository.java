package com.example.haushaltsverwaltungssystem.repository;

import com.example.haushaltsverwaltungssystem.domain.SocialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialGroupRepository extends JpaRepository<SocialGroup, Long> { }

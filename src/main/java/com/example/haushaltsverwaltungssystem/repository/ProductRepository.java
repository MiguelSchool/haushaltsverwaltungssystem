package com.example.haushaltsverwaltungssystem.repository;

import com.example.haushaltsverwaltungssystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {  }

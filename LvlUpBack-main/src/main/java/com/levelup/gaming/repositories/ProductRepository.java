package com.levelup.gaming.repositories;

import com.levelup.gaming.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByIsActiveTrue();

    List<Product> findByCategory(String category);
}

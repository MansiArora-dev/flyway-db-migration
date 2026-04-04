package com.springboot.flyway.dbmigration.repositories;

import com.springboot.flyway.dbmigration.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}
package com.springboot.microservices.inventory_service.repository;

import com.springboot.microservices.inventory_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
package com.example.wildfly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wildfly.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}

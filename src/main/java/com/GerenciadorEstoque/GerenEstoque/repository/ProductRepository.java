package com.GerenciadorEstoque.GerenEstoque.repository;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT ph FROM Product ph WHERE ph.isInactivated = false")
    List<Product> findAllByIsActived();
    Optional<Product> findBySku(String sku);
    Boolean existsBySku(String sku);
}

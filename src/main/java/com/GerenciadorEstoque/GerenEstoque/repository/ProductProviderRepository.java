package com.GerenciadorEstoque.GerenEstoque.repository;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductProviderRepository extends JpaRepository<ProductProvider, Integer> {
}

package com.GerenciadorEstoque.GerenEstoque.repository;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}

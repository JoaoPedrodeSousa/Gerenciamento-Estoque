package com.GerenciadorEstoque.GerenEstoque.repository;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer> {
}

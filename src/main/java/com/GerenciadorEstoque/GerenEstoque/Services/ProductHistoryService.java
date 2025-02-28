package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHistoryService {
    @Autowired
    private ProductHistoryRepository historyRepository;

    public List<ProductHistory> findBySku(String sku){
        return historyRepository.findBySku(sku);
    }

    public ProductHistory findByUuid(String uuid){
        return historyRepository.findByUuid(uuid);
    }
}

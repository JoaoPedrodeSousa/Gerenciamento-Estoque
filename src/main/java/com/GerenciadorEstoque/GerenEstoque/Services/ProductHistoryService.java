package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductHistoryResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductHistoryService {
    @Autowired
    private ProductHistoryRepository historyRepository;

    public List<ProductHistoryResponseDTO> findBySku(String sku) {
        return historyRepository.findBySku(sku)
                .stream()
                .map(ProductHistoryResponseDTO::new)
                .collect(Collectors.toList());
    }
}

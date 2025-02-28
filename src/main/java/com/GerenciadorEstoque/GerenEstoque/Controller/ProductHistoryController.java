package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductHistoryResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/history")
public class ProductHistoryController {

    @Autowired
    private ProductHistoryService historyService;

    @GetMapping(value = "/{sku}")
    public ResponseEntity<List<ProductHistoryResponseDTO>> findAll(@PathVariable String sku){
        List<ProductHistoryResponseDTO> histories = historyService.findBySku(sku);
        return ResponseEntity.ok().body(histories);
    }

}

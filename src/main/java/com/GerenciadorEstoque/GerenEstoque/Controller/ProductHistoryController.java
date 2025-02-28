package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{sku}")
public class ProductHistoryController {

    @Autowired
    private ProductHistoryService historyService;

    @GetMapping(value = "/h")
    public ResponseEntity<List<ProductHistory>> findAll(@PathVariable String sku){
        List<ProductHistory> histories = historyService.findBySku(sku);
        return ResponseEntity.ok().body(histories);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ProductHistory> findByUuid(@PathVariable String uuid){
        ProductHistory history = historyService.findByUuid(uuid);
        return ResponseEntity.ok().body(history);
    }
}

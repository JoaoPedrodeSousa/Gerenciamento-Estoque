package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductHistoryResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/history/{sku}")
public class ProductHistoryController {

    @Autowired
    private ProductHistoryService historyService;
    @GetMapping
    @Operation(summary = "Return logs with the same code SKU.")
    @ApiResponse(responseCode = "200", description = "Return Success.")
    public ResponseEntity<List<ProductHistoryResponseDTO>> findAll(@PathVariable String sku){
        List<ProductHistoryResponseDTO> histories = historyService.findBySku(sku);
        return ResponseEntity.ok().body(histories);
    }

}

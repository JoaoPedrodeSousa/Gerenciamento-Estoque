package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id){
        ProductResponseDTO productRequestDTO = productService.findById(id);
        return ResponseEntity.ok().body(productRequestDTO);
    }

    @GetMapping(value = "/sku/{sku}")
    public ResponseEntity<ProductResponseDTO> findBySku(@PathVariable String sku){
        ProductResponseDTO productRequestDTO = productService.findBySku(sku);
        return ResponseEntity.ok().body(productRequestDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ProductRequestDTO productRequestDTO){
        productService.insert(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{sku}")
    public ResponseEntity<Void> update(@PathVariable String sku,
                                       @RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO product = productService.update(sku, productRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/sku/{sku}")
    public ResponseEntity<Void> deleteBySku(@PathVariable String sku){
        productService.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }

}

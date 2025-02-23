package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductRequestDTO> findById(@PathVariable Integer id){
        Product product = productService.findById(id);
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(product);

        return ResponseEntity.ok().body(productRequestDTO);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> insert(@RequestBody ProductRequestDTO productRequestDTO){
        productService.insert(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("value = {SKU}")
    public ResponseEntity<Void> update(@PathVariable String SKU,
                                       @RequestBody ProductRequestDTO productRequestDTO){
        Product product = productService.update(productRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

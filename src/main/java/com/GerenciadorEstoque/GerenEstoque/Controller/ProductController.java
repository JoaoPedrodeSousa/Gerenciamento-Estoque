package com.GerenciadorEstoque.GerenEstoque.Controller;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "product", description = "Controller for save and edit dates of product.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Return all products")
    @ApiResponse(responseCode = "200", description = "Products was successfully found")
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<Product> products = productService.findAll();
        List<ProductResponseDTO> productsDTO = new ArrayList<>();

        products.forEach(product -> productsDTO.add(new ProductResponseDTO(product)));

        return ResponseEntity.ok().body(productsDTO);
    }

    @GetMapping(value = "/{sku}")
    @Operation(summary = "Return products with the same SKU.")
    @ApiResponse(responseCode = "200", description = "Product was successfully found")
    @ApiResponse(responseCode = "404", description = "Product has .")
    public ResponseEntity<ProductResponseDTO> findBySku(@PathVariable String sku){
        ProductResponseDTO productRequestDTO = productService.findBySku(sku);
        return ResponseEntity.ok().body(productRequestDTO);
    }

    @PostMapping
    @Operation(summary = "Create a new product in the database.")
    @ApiResponse(responseCode = "201", description = "Create Product Success.")
    @ApiResponse(responseCode = "400", description = "Product has invalid data.")
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO responseDTO = productService.insert(productRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping(value = "/{sku}")
    @Operation(summary = "Update an existing product using its SKU.")
    @ApiResponse(responseCode = "204", description = "Update Product Success.")
    @ApiResponse(responseCode = "404", description = " Product not found.")
    public ResponseEntity<Void> update(@PathVariable String sku,
                                       @RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO product = productService.update(sku, productRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{sku}")
    @Operation(summary = "Delete an existing product using its SKU.")
    @ApiResponse(responseCode = "204", description = "Delete Product Success.")
    @ApiResponse(responseCode = "404", description = " Product not found.")
    public ResponseEntity<Void> deleteBySku(@PathVariable String sku){
        productService.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }
}

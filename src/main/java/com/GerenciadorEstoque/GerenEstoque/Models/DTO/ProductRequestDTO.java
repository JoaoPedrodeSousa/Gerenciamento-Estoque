package com.GerenciadorEstoque.GerenEstoque.Models.DTO;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import jakarta.persistence.Column;

public class ProductRequestDTO {
    private String SKU;

    private String name;

    private String description;

    private String productCategoryName;

    private Double price;

    private Integer minimumForReplacement;

    private Integer quantity;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(Product product){
        this.SKU = product.getSKU();
        this.name = product.getName();
        this.description = product.getDescription();
        this.productCategoryName = product.getProductCategory().getCategoryName();
        this.price = product.getPrice();
        this.minimumForReplacement = product.getMinimumForReplacement();
        this.quantity = product.getQuantity();
    }

    public String getSKU() {
        return SKU;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getMinimumForReplacement() {
        return minimumForReplacement;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

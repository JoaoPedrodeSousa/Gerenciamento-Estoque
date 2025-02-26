package com.GerenciadorEstoque.GerenEstoque.Models.DTO;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;

import java.util.Date;

public class ProductRequestDTO {
    private String SKU;

    private String name;

    private String description;

    private String productCategoryName;

    private Double price;

    private Integer minimumForReplacement;

    private Integer quantity;
    private Date dateOfRegister;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String SKU,
                             String name,
                             String description,
                             String productCategoryName,
                             Double price,
                             Integer minimumForReplacement,
                             Integer quantity) {
        this.SKU = SKU;
        this.name = name;
        this.description = description;
        this.productCategoryName = productCategoryName;
        this.price = price;
        this.minimumForReplacement = minimumForReplacement;
        this.quantity = quantity;
        this.dateOfRegister = new Date();
    }

    public ProductRequestDTO(Product product){
        this.SKU = product.getSku();
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

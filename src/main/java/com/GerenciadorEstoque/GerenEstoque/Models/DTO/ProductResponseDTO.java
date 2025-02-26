package com.GerenciadorEstoque.GerenEstoque.Models.DTO;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;

import java.util.Date;

public class ProductResponseDTO {
    private String SKU;

    private String name;

    private String description;

    private String productCategoryName;

    private Double price;

    private Integer minimumForReplacement;

    private Integer quantity;
    private Date dateOfRegister;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product product){
        this.SKU = product.getSku();
        this.name = product.getName();
        this.description = product.getDescription();
        this.productCategoryName = product.getProductCategory().getCategoryName();
        this.price = product.getPrice();
        this.dateOfRegister = product.getDateOfRegister();
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

    public Date getDateOfRegister() {
        return dateOfRegister;
    }
}

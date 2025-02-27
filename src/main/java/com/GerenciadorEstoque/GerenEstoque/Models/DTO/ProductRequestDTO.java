package com.GerenciadorEstoque.GerenEstoque.Models.DTO;

import java.util.Date;

public class ProductRequestDTO {

    private final String name;

    private final String description;

    private final String categoryName;

    private final Double price;

    private final Integer minimumForReplacement;

    private final Integer quantity;

    private final Date dateOfRegister;

    private final String provider;

    public ProductRequestDTO(
                             String name,
                             String description,
                             String categoryName,
                             String provider,
                             Double price,
                             Integer minimumForReplacement,
                             Integer quantity) {
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.provider = provider;
        this.price = price;
        this.minimumForReplacement = minimumForReplacement;
        this.quantity = quantity;
        this.dateOfRegister = new Date();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public String getProvider() {
        return provider;
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

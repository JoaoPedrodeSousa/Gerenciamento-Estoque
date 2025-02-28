package com.GerenciadorEstoque.GerenEstoque.Models.DTO;

import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;

import java.util.Date;

public class ProductHistoryResponseDTO {

    private final String uuid;

    private final String productName;

    private final String sku;

    private final Date dateOfReplacement;

    private final Integer numberOfReplacementProducts;


    public ProductHistoryResponseDTO(String uuid,
                                     String productName,
                                     Date dateOfReplacement,
                                     Integer numberOfReplacementProducts,
                                     String sku) {
        this.uuid = uuid;
        this.sku = sku;
        this.productName = productName;
        this.dateOfReplacement = dateOfReplacement;
        this.numberOfReplacementProducts = numberOfReplacementProducts;
    }

    public ProductHistoryResponseDTO(ProductHistory productHistory) {
        this.uuid = productHistory.getUuid();
        this.sku = productHistory.getProduct().getSku();
        this.productName = productHistory.getProduct().getName();
        this.dateOfReplacement = productHistory.getDateOfReplacement();
        this.numberOfReplacementProducts = productHistory.getNumberOfReplacementProducts();
    }

    public String getUuid() {
        return uuid;
    }

    public String getProductName() {
        return productName;
    }

    public Date getDateOfReplacement() {
        return dateOfReplacement;
    }

    public Integer getNumberOfReplacementProducts() {
        return numberOfReplacementProducts;
    }

    public String getSku() {
        return sku;
    }
}

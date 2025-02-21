package com.GerenciadorEstoque.GerenEstoque.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Product implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String SKU;
    private String name;
    private String description;
    @JoinColumn(name = "productCategory_id")
    private ProductCategory category;

    private List<ProductProvider> providers = new ArrayList<>();
    private Double price;
    private Integer minimumForReplacement;
    private Date dateOfRegister;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   String description,
                   ProductCategory category,
                   List<ProductProvider> providers,
                   Double price,
                   Integer minimumForReplacement,
                   Date dateOfRegister) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.providers = providers;
        this.price = price;
        this.minimumForReplacement = minimumForReplacement;
        this.dateOfRegister = dateOfRegister;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProductProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<ProductProvider> providers) {
        this.providers = providers;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMinimumForReplacement() {
        return minimumForReplacement;
    }

    public void setMinimumForReplacement(Integer minimumForReplacement) {
        this.minimumForReplacement = minimumForReplacement;
    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product products)) return false;
        return Objects.equals(id, products.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", providers=" + providers +
                ", price=" + price +
                ", minimumForReplacement=" + minimumForReplacement +
                ", dateOfRegister=" + dateOfRegister +
                '}';
    }
}

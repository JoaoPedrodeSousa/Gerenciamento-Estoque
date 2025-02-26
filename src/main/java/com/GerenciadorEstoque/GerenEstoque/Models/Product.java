package com.GerenciadorEstoque.GerenEstoque.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer id;

    @Column(unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToMany
    @JoinTable(name = "product_provider_relation",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "provider_id"))
    private List<ProductProvider> providers = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductHistory> histories = new ArrayList<>();

    @Column(nullable = false)
    private Double price;

    private Integer minimumForReplacement;

    @Column(nullable = false)
    private Integer quantity;
    private final Date dateOfRegister;

    public Product() {
        this.dateOfRegister = new Date();
    }

    public Product(Integer id,
                   String name,
                   String description,
                   ProductCategory productCategory,
                   Double price,
                   Integer minimumForReplacement,
                   Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.price = price;
        this.minimumForReplacement = minimumForReplacement;
        this.dateOfRegister = new Date();
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<ProductHistory> histories) {
        this.histories = histories;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productCategory=" + productCategory +
                ", providers=" + providers +
                ", price=" + price +
                ", minimumForReplacement=" + minimumForReplacement +
                ", dateOfRegister=" + dateOfRegister +
                '}';
    }
}

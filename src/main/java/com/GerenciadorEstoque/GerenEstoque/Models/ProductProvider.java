package com.GerenciadorEstoque.GerenEstoque.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ProductProvider implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ProductProviderName;

    @ManyToMany(mappedBy = "providers")
    private List<Product> products = new ArrayList<>();

    public ProductProvider() {
    }

    public ProductProvider(Integer id, String productProviderName, List<Product> products) {
        this.id = id;
        ProductProviderName = productProviderName;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductProviderName() {
        return ProductProviderName;
    }

    public void setProductProviderName(String productProviderName) {
        ProductProviderName = productProviderName;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductProvider that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductProvider{" +
                "id=" + id +
                ", ProductProviderName='" + ProductProviderName + '\'' +
                ", products=" + products +
                '}';
    }
}

package com.GerenciadorEstoque.GerenEstoque.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ProductProvider implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ProductProviderName;

    public ProductProvider(Integer id, String productProviderName) {
        this.id = id;
        ProductProviderName = productProviderName;
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
                '}';
    }
}

package com.GerenciadorEstoque.GerenEstoque.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class ProductHistory implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private Date dateOfReplacement;

    public ProductHistory() {
    }

    public ProductHistory(Long id, Product product, Date dateOfReplacement) {
        this.id = id;
        this.product = product;
        this.dateOfReplacement = dateOfReplacement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateOfReplacement() {
        return dateOfReplacement;
    }

    public void setDateOfReplacement(Date dateOfReplacement) {
        this.dateOfReplacement = dateOfReplacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductHistory that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

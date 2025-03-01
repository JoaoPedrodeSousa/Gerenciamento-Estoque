package com.GerenciadorEstoque.GerenEstoque.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class ProductHistory implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_sku",referencedColumnName = "sku")
    private Product product;
    private Date dateOfReplacement;
    private Integer numberOfReplacementProducts;

    public ProductHistory() {
    }

    public ProductHistory(Integer id, Product product, Date dateOfReplacement, Integer numberOfReplacementProducts) {
        this.id = id;
        this.product = product;
        this.dateOfReplacement = dateOfReplacement;
        this.numberOfReplacementProducts = numberOfReplacementProducts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getNumberOfReplacementProducts() {
        return numberOfReplacementProducts;
    }

    public void setNumberOfReplacementProducts(Integer numberOfReplacementProducts) {
        this.numberOfReplacementProducts = numberOfReplacementProducts;
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

    @Override
    public String toString() {
        return "ProductHistory{" +
                "id=" + id +
                ", product=" + product +
                ", dateOfReplacement=" + dateOfReplacement +
                ", numberOfReplacementProducts=" + numberOfReplacementProducts +
                '}';
    }
}

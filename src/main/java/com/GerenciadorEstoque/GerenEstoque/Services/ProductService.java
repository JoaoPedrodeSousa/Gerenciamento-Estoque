package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.Utils.DateFormat;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductHistoryRepository;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements GenericProductService<Product,Integer>{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductHistoryRepository historyRepository;

    @Override
    public Product findById(Integer entityId) {
        Optional<Product> optionalProduct = productRepository.findById(entityId);

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product insert(Product entity) {
        validateProduct(entity);

        Product product = productRepository.save(entity);
        ProductHistory history = new ProductHistory(null, product, DateFormat.getInstance(), product.getQuantity());
        historyRepository.save(history);
        return product;
    }

    @Override
    public Product update(Product entity) {
        Optional<Product> optionalProduct = productRepository.findById(entity.getId());

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        Product product = optionalProduct.get();

        Integer newQuantity = entity.getQuantity() - product.getQuantity();

        if(!newQuantity.equals(0)){
            ProductHistory history = new ProductHistory(null,product, DateFormat.getInstance(),newQuantity);
            historyRepository.save(history);
        }

        updateData(product, entity);
        productRepository.save(product);
        return product;
    }

    private void validateProduct(Product entity) {
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio.");
        }
        if (entity.getPrice() == null) {
            throw new IllegalArgumentException("O preço do produto não pode estar vazio.");
        }
        if (entity.getQuantity() == null) {
            throw new IllegalArgumentException("A quantidade do produto não pode estar vazia");
        }
    }

    private void updateData(Product product, Product entity){
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setProductCategory(entity.getProductCategory());
        product.setProviders(entity.getProviders());
        product.setPrice(entity.getPrice());
    }

    @Override
    public void delete(Integer entityId) {
        productRepository.deleteById(entityId);
    }
}

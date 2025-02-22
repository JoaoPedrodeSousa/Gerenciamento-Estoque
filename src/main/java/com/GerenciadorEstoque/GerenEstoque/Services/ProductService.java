package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements GenericProductService<Product,Integer>{

    @Autowired
    private ProductRepository productRepository;

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
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product insert(Product entity) {
        Product product = productRepository.save(entity);
        return product;
    }

    @Override
    public Product update(Product entity) {
        Optional<Product> optionalProduct = productRepository.findById(entity.getId());

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }
        Product product = optionalProduct.get();
        updateData(product, entity);
        return null;
    }

    private void updateData(Product product, Product entity){
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setCategory(entity.getCategory());
        product.setProviders(entity.getProviders());
        product.setPrice(entity.getPrice());
    }

    @Override
    public void delete(Integer entityId) {
        productRepository.deleteById(entityId);
    }


}

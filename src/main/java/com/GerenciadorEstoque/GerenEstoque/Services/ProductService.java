package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductCategory;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductCategoryRepository;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductHistoryRepository;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductHistoryRepository historyRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public Product findById(Integer entityId) {
        Optional<Product> optionalProduct = productRepository.findById(entityId);

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        return optionalProduct.get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insert(ProductRequestDTO entityDTO) {

        validateProduct(entityDTO);
        Product entity = fromDTO(entityDTO);

        String categoryName = entityDTO.getProductCategoryName();
        Optional<ProductCategory> category = categoryRepository.findByCategoryName(categoryName);

        if(category.isEmpty()){
            ProductCategory newCategory = new ProductCategory(null, entityDTO.getProductCategoryName());
            newCategory = categoryRepository.save(newCategory);

            entity.setProductCategory(newCategory);
        }
        else{
            entity.setProductCategory(category.get());
        }

        Product product = productRepository.save(entity);
        ProductHistory history = new ProductHistory(null, product, new Date(), product.getQuantity());
        historyRepository.save(history);

        return product;
    }

    public Product update(ProductRequestDTO entityDTO) {
        Optional<Product> optionalProduct = productRepository.findBySku(entityDTO.getSKU());

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        Product product = optionalProduct.get();

        updateData(product, entityDTO);
        productRepository.save(product);
        return product;
    }

    public void delete(Integer entityId) {
        productRepository.deleteById(entityId);
    }

    private void validateProduct(ProductRequestDTO entity) {
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

    private Product fromDTO(ProductRequestDTO entityDTO){
        Product entity = new Product();

        entity.setName(entityDTO.getName());
        entity.setDescription(entityDTO.getDescription());
        entity.setPrice(entityDTO.getPrice());
        entity.setMinimumForReplacement(entityDTO.getMinimumForReplacement());
        entity.setQuantity(entityDTO.getQuantity());

        return entity;
    }

    private void updateData(Product product, ProductRequestDTO entityDTO){
        product.setName(entityDTO.getName());
        product.setDescription(entityDTO.getDescription());
/*        product.setProductCategory(entityDTO.getProductCategory());
        product.setProviders(entityDTO.getProviders());*/
        product.setPrice(entityDTO.getPrice());
        product.setQuantity(entityDTO.getQuantity());
        product.setMinimumForReplacement(entityDTO.getMinimumForReplacement());

        ProductHistory history = new ProductHistory(null, product, new Date(), product.getQuantity());
        historyRepository.save(history);
    }

}

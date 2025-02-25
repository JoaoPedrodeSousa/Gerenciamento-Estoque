package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductResponseDTO;
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

    public ProductResponseDTO findById(Integer entityId) {
        Optional<Product> optionalProduct = productRepository.findById(entityId);

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        return new ProductResponseDTO(optionalProduct.get());
    }

    public ProductResponseDTO findBySku(String sku) {
        Optional<Product> optionalProduct = productRepository.findBySku(sku);

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        return new ProductResponseDTO(optionalProduct.get());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductResponseDTO insert(ProductRequestDTO productDTO) {

        validateProduct(productDTO);
        Product product = fromDTO(productDTO);

        String categoryName = productDTO.getProductCategoryName();
        Optional<ProductCategory> category = categoryRepository.findByCategoryName(categoryName);

        if(category.isEmpty()){
            ProductCategory newCategory = new ProductCategory(null, productDTO.getProductCategoryName());
            newCategory = categoryRepository.save(newCategory);

            product.setProductCategory(newCategory);
        }
        else{
            product.setProductCategory(category.get());
        }

        Product newProduct = productRepository.save(product);

        saveProductHistory(newProduct);

        return new ProductResponseDTO(newProduct);
    }

    public ProductResponseDTO update(ProductRequestDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findBySku(productDTO.getSKU());

        if (optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Product Not Found");
        }

        Product product = optionalProduct.get();

        updateData(product, productDTO);
        productRepository.save(product);

        return new ProductResponseDTO(product);
    }

    public void deleteById(Integer entityId) {
        if (!productRepository.existsById(entityId)) {
            throw new EntityNotFoundException("Product Not Found");
        }
        productRepository.deleteById(entityId);
    }

    public void deleteBySku(String sku) {
        if (!productRepository.existsBySku(sku)) {
            throw new EntityNotFoundException("Product Not Found");
        }
        productRepository.deleteBySku(sku);
    }

    private void validateProduct(ProductRequestDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio.");
        }
        if (productDTO.getPrice() == null) {
            throw new IllegalArgumentException("O preço do produto não pode estar vazio.");
        }
        if (productDTO.getQuantity() == null) {
            throw new IllegalArgumentException("A quantidade do produto não pode estar vazia");
        }
    }

    private Product fromDTO(ProductRequestDTO productDTO){
        Product product = new Product();
        fromProductDTOToProduct(product, productDTO);

        return product;
    }

    private void updateData(Product product, ProductRequestDTO productDTO){
        fromProductDTOToProduct(product, productDTO);
        saveProductHistory(product);
    }



    private void fromProductDTOToProduct(Product product, ProductRequestDTO productDTO){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setMinimumForReplacement(productDTO.getMinimumForReplacement());
    }

    private void saveProductHistory(Product product) {
        ProductHistory history = new ProductHistory(null, product, new Date(), product.getQuantity());
        historyRepository.save(history);
    }
}

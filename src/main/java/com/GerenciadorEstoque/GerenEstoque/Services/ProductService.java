package com.GerenciadorEstoque.GerenEstoque.Services;

import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductRequestDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.DTO.ProductResponseDTO;
import com.GerenciadorEstoque.GerenEstoque.Models.Product;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductCategory;
import com.GerenciadorEstoque.GerenEstoque.Models.ProductHistory;
import com.GerenciadorEstoque.GerenEstoque.Utils.GenerateSKU;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductCategoryRepository;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductHistoryRepository;
import com.GerenciadorEstoque.GerenEstoque.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductHistoryRepository historyRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAllByIsActived();
    }

    public ProductResponseDTO findBySku(String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        if (product.isInactivated()){
            throw new EntityNotFoundException("Product Not Found");
        }

        return new ProductResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO insert(ProductRequestDTO productDTO) {
        validateInsertProduct(productDTO);

        Product product = convertToProduct(productDTO);
        product.setSku(generateSku(product.getName()));

        ProductCategory category = getOrCreateCategory(productDTO.getCategoryName());
        product.setProductCategory(category);

        Product savedProduct = persistProduct(product);

        return new ProductResponseDTO(savedProduct);
    }

    @Transactional
    public ProductResponseDTO updateInformation(String sku, ProductRequestDTO productDTO) {
        Product product = productRepository.findBySku(sku)
                                            .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        updateDataFromProductDTOToProduct(product, productDTO);
        productRepository.save(product);

        return new ProductResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO updateQuantityAndPrice(String sku, ProductRequestDTO productDTO) {

        Product product = productRepository.findBySku(sku)
                                            .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

        validateUpdateProduct(product, productDTO);

        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);
        saveProductHistory(product);

        return new ProductResponseDTO(product);
    }


    @Transactional
    public void softDeleteBySku(String sku) {
        if (!productRepository.existsBySku(sku)) {
            throw new EntityNotFoundException("Product Not Found");
        }

        Product product = productRepository.findBySku(sku).get();
        product.setInactivated(true);
        productRepository.save(product);
    }

    private Product convertToProduct(ProductRequestDTO productDTO) {
        return new Product(null,
                    productDTO.getName(),
                    productDTO.getDescription(),
                    null,
                    productDTO.getProvider(),
                    productDTO.getPrice(),
                    productDTO.getMinimumForReplacement(),
                    productDTO.getQuantity());
    }

    private String generateSku(String productName) {
        return GenerateSKU.setSKU(productName);
    }

    private ProductCategory getOrCreateCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseGet(() -> categoryRepository.save(new ProductCategory(null, categoryName)));
    }

    private Product persistProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        saveProductHistory(savedProduct);
        return savedProduct;
    }

    private void validateInsertProduct(ProductRequestDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isBlank() || productDTO.getProvider().isBlank()) {
            throw new IllegalArgumentException("The name of product can't be null or blank.");
        }
        if (productDTO.getPrice() == null) {
            throw new IllegalArgumentException("The price of product can't be null.");
        }
        if (productDTO.getQuantity() == null) {
            throw new IllegalArgumentException("The quantity of product can't be null.");
        }

        if (productDTO.getMinimumForReplacement() == null) {
            throw new IllegalArgumentException("The Minimum for replacement of product can't be null, equals or less than zero.");
        }
    }
    private void validateUpdateProduct(Product product, ProductRequestDTO productDTO) {

        if (product.isInactivated()) {
            throw new EntityNotFoundException("Product Not Found");
        }

        if (productDTO.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (productDTO.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be null or less than zero.");
        }
    }

    private void updateDataFromProductDTOToProduct(Product product, ProductRequestDTO productDTO){
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setMinimumForReplacement(productDTO.getMinimumForReplacement());
        product.setProvider(productDTO.getProvider());
    }

    private void saveProductHistory(Product product) {
        ProductHistory history = new ProductHistory(null, product, new Date(), product.getQuantity());
        historyRepository.save(history);
    }
}

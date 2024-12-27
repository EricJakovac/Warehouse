package com.backend.warehouse.service;

import com.backend.warehouse.model.Product;
import com.backend.warehouse.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByWarehouseId(Long warehouseId) {
        return productRepository.findByWarehouse_WarehouseId(warehouseId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, @NotNull Product productDetails) {
        Product product = productRepository.findById(productId).orElseThrow();

        product.setProductName(Objects.requireNonNull(productDetails).getProductName());
        product.setProductCode(Objects.requireNonNull(productDetails).getProductCode());
        product.setProductQuantity(Objects.requireNonNull(productDetails).getProductQuantity());
        product.setProductMinQuantity(Objects.requireNonNull(productDetails).getProductMinQuantity());
        product.setProductPrice(Objects.requireNonNull(productDetails).getProductPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

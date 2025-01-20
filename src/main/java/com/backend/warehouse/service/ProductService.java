package com.backend.warehouse.service;

import com.backend.warehouse.model.*;
import com.backend.warehouse.repository.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public ProductService(ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
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

    public Product addProduct(Product product, Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));

        product.setWarehouse(warehouse);
        return productRepository.save(product);
    }

    public Product updateProduct(String productCode, @NotNull Product productDetails) {
        Product product = productRepository.findByProductCode(productCode).orElseThrow();

        product.setProductName(Objects.requireNonNull(productDetails).getProductName());
        product.setProductCode(Objects.requireNonNull(productDetails).getProductCode());
        product.setProductQuantity(Objects.requireNonNull(productDetails).getProductQuantity());
        product.setProductMinQuantity(Objects.requireNonNull(productDetails).getProductMinQuantity());
        product.setProductPrice(Objects.requireNonNull(productDetails).getProductPrice());
        product.setProductArriveDate(Objects.requireNonNull(productDetails).getProductArriveDate());
        product.setProductDepartureDate(Objects.requireNonNull(productDetails).getProductDepartureDate());

        return productRepository.save(product);
    }

    public void deleteProduct(String productCode) {
        productRepository.deleteByProductCode(productCode);
    }
}

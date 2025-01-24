package com.backend.warehouse.service;

import com.backend.warehouse.event.ProductQuantityUpdateEvent;
import com.backend.warehouse.model.*;
import com.backend.warehouse.repository.*;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductService(ProductRepository productRepository, WarehouseRepository warehouseRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByWarehouseId(Long warehouseId, boolean sortByName) {
        Sort sort = sortByName ? Sort.by(Sort.Order.asc("productName")) : Sort.unsorted();
        return productRepository.findByWarehouse_WarehouseId(warehouseId, sort);
    }

    public Product getProductByProductCode(String productCode) {
        return productRepository.findByProductCode(productCode).orElseThrow();
    }

    public Product addProduct(Product product, Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));

        product.setWarehouse(warehouse);
        return productRepository.save(product);
    }

    public Product updateProduct(String productCode, @NotNull Product productDetails) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Proizvod nije pronađen"));

        product.setProductName(Objects.requireNonNull(productDetails).getProductName());
        product.setProductCode(Objects.requireNonNull(productDetails).getProductCode());
        product.setProductQuantity(Objects.requireNonNull(productDetails).getProductQuantity());
        product.setProductMinQuantity(Objects.requireNonNull(productDetails).getProductMinQuantity());
        product.setProductPrice(Objects.requireNonNull(productDetails).getProductPrice());
        product.setProductArriveDate(Objects.requireNonNull(productDetails).getProductArriveDate());
        product.setProductDepartureDate(Objects.requireNonNull(productDetails).getProductDepartureDate());

        Product savedProduct = productRepository.save(product);

        eventPublisher.publishEvent(new ProductQuantityUpdateEvent(this, savedProduct));

        return savedProduct;
    }


    public Product updateProductQuantity(String productCode, int newQuantity) {
        Product product = productRepository.findByProductCode(productCode).orElseThrow();

        product.setProductQuantity(newQuantity);
        productRepository.save(product);
        eventPublisher.publishEvent(new ProductQuantityUpdateEvent(this, product));
        System.out.println("Događaj emitiran za proizvod: " + product.getProductName());
        return product;
    }

    @Transactional
    public void deleteByProductCode(String productCode) {
        productRepository.deleteByProductCode(productCode);
    }
}

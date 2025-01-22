package com.backend.warehouse.controller;

import com.backend.warehouse.dto.ProductRequest;
import com.backend.warehouse.model.Product;
import com.backend.warehouse.service.ProductService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productCode}")
    public Product getProductByProductCode(@PathVariable String productCode) {
        return productService.getProductByProductCode(productCode);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<Product> getProductsByWarehouse(@PathVariable Long warehouseId){
        return productService.getProductsByWarehouseId(warehouseId);
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductCode(productRequest.getProductCode());
        product.setProductQuantity(productRequest.getProductQuantity());
        product.setProductMinQuantity(productRequest.getProductMinQuantity());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductArriveDate(productRequest.getProductArriveDate());
        product.setProductDepartureDate(productRequest.getProductDepartureDate());

        return productService.addProduct(product, productRequest.getWarehouseId());
    }

    @PutMapping("/{productCode}")
    public Product updateProduct(@PathVariable @Pattern(regexp = "^[0-9]{6}$") String productCode, @RequestBody Product product) {
        return productService.updateProduct(productCode, product);
    }

    @DeleteMapping("/{productCode}")
    public void deleteProduct(@PathVariable @Pattern(regexp = "^[0-9]{6}$") String productCode) {
        productService.deleteByProductCode(productCode);
    }

}

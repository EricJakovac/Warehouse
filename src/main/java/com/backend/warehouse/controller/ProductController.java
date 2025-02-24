package com.backend.warehouse.controller;

import com.backend.warehouse.dto.ProductDTO;
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

    @GetMapping("/warehouse/{warehouseId}")
    public List<Product> getProductsByWarehouse(@PathVariable Long warehouseId, @RequestParam(defaultValue = "true") boolean sortByName) {
        return productService.getProductsByWarehouseId(warehouseId, sortByName);
    }

    @GetMapping("/{productCode}")
    public ProductDTO getProductByProductCode(@PathVariable String productCode) {
        Product product = productService.getProductByProductCode(productCode);
        return ProductDTO.fromEntity(product);
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductCode(productDTO.getProductCode());
        product.setProductQuantity(productDTO.getProductQuantity());
        product.setProductMinQuantity(productDTO.getProductMinQuantity());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductArriveDate(productDTO.getProductArriveDate());
        product.setProductDepartureDate(productDTO.getProductDepartureDate());

        return productService.addProduct(product, productDTO.getWarehouseId());
    }

    @PutMapping("/{productCode}")
    public ProductDTO updateProduct(
            @PathVariable @Pattern(regexp = "^[0-9]{6}$") String productCode,
            @RequestBody ProductDTO productDTO) {

        Product updatedProduct = productService.updateProduct(productCode, ProductDTO.toEntity(productDTO), productDTO.getWarehouseId());
        return ProductDTO.fromEntity(updatedProduct);
    }

    @DeleteMapping("/{productCode}")
    public void deleteProduct(@PathVariable @Pattern(regexp = "^[0-9]{6}$") String productCode) {
        productService.deleteByProductCode(productCode);
    }

}

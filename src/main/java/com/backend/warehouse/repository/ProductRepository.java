package com.backend.warehouse.repository;

import com.backend.warehouse.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Dodatne operacije koje ne stvara JpaRepostory automatski
    List<Product> findByWarehouse_WarehouseId(Long warehouseId, Sort sort);
    Optional<Product> findByProductCode(String productCode);
    void deleteByProductCode(String productCode);
}

package com.backend.warehouse.repository;

import com.backend.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository automatski stvara oepracije za save, findById, findAll i deleteById
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByLocation(String warehouseLocation);
}

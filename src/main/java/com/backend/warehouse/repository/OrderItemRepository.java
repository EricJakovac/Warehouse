package com.backend.warehouse.repository;

import com.backend.warehouse.model.OrderItem;
import com.backend.warehouse.model.Product;
import com.backend.warehouse.model.WarehouseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByWarehouseOrder(WarehouseOrder warehouseOrder);
    Optional<OrderItem> findByWarehouseOrderAndProduct(WarehouseOrder warehouseOrder, Product product);
}

package com.backend.warehouse.repository;

import com.backend.warehouse.dto.OrderDTO;
import com.backend.warehouse.dto.OrderWithItemDTO;
import com.backend.warehouse.model.WarehouseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseOrderRepository extends JpaRepository<WarehouseOrder, Long> {

    @Query("SELECT new com.backend.warehouse.dto.OrderWithItemDTO(" +
            "wo.orderId, wo.orderDate, wo.status, " +
            "oi.orderItemId, oi.product.productCode, oi.quantity) " +
            "FROM WarehouseOrder wo " +
            "JOIN wo.orderItems oi " +
            "WHERE wo.warehouse.warehouseId = :warehouseId")
    List<OrderWithItemDTO> findOrdersWithItemsByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query("SELECT new com.backend.warehouse.dto.OrderWithItemDTO(" +
            "wo.orderId, wo.orderDate, wo.status, " +
            "oi.orderItemId, oi.product.productCode, oi.quantity) " +
            "FROM WarehouseOrder wo " +
            "JOIN wo.orderItems oi")
    List<OrderWithItemDTO> findOrdersWithItems();

}

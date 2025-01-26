package com.backend.warehouse.controller;

import com.backend.warehouse.dto.OrderDTO;
import com.backend.warehouse.model.WarehouseOrder;
import com.backend.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class WarehouseOrderController {

    private final OrderService orderService;

    @Autowired
    public WarehouseOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<OrderDTO> getOrdersForWarehouse(@PathVariable Long warehouseId) {
        return orderService.getOrders(warehouseId);
    }

    @PutMapping("/{orderId}/confirm")
    public void confirmOrder(@PathVariable Long orderId) {
        orderService.confirmOrder(orderId);
    }

    @PutMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }

    @PutMapping("/{orderId}/reorder")
    public void reorder(@PathVariable Long orderId) {
        orderService.reorder(orderId);
    }
}

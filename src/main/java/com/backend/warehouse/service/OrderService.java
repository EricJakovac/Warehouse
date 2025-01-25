package com.backend.warehouse.service;

import com.backend.warehouse.dto.*;
import com.backend.warehouse.model.*;
import com.backend.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    private final WarehouseOrderRepository warehouseOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(WarehouseOrderRepository warehouseOrderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.warehouseOrderRepository = warehouseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public void createOrderForProduct(Product product, int quantity) {
        WarehouseOrder warehouseOrder = new WarehouseOrder();
        warehouseOrder.setOrderDate(LocalDate.now());
        warehouseOrder.setStatus("PENDING");
        warehouseOrder.setWarehouse(product.getWarehouse());

        warehouseOrderRepository.save(warehouseOrder);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setProduct(product);
        orderItem.setWarehouseOrder(warehouseOrder);

        orderItemRepository.save(orderItem);
    }

    public void confirmOrder(Long orderId) {
        WarehouseOrder warehouseOrder = warehouseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!"PENDING".equals(warehouseOrder.getStatus())) {
            throw new IllegalStateException("Only PENDING orders can be confirmed");
        }

        warehouseOrder.setStatus("CONFIRMED");
        warehouseOrderRepository.save(warehouseOrder);

        List<OrderItem> orderItems = orderItemRepository.findByWarehouseOrder(warehouseOrder);
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            product.setProductQuantity(product.getProductQuantity() + item.getQuantity());
            productRepository.save(product);
        }
    }

    public void cancelOrder(Long orderId) {
        WarehouseOrder warehouseOrder = warehouseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!"PENDING".equals(warehouseOrder.getStatus())) {
            throw new IllegalStateException("Only PENDING orders can be canceled");
        }

        warehouseOrder.setStatus("CANCELED");
        warehouseOrderRepository.save(warehouseOrder);
    }

    public void reorder(Long orderId) {
        WarehouseOrder oldOrder = warehouseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!"CANCELED".equals(oldOrder.getStatus())) {
            throw new IllegalStateException("Only CANCELED orders can be reordered");
        }

        oldOrder.setStatus("PENDING");
        warehouseOrderRepository.save(oldOrder);
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderWithItemDTO> rawData = warehouseOrderRepository.findOrdersWithItems();
        return mapOrdersWithItems(rawData);
    }

    public List<OrderDTO> getOrders(Long warehouseId) {
        List<OrderWithItemDTO> rawData = warehouseOrderRepository.findOrdersWithItemsByWarehouseId(warehouseId);
        return mapOrdersWithItems(rawData);
    }

    private List<OrderDTO> mapOrdersWithItems(List<OrderWithItemDTO> rawData) {
        Map<Long, OrderDTO> orderMap = new HashMap<>();
        for (OrderWithItemDTO item : rawData) {
            orderMap.computeIfAbsent(item.getOrderId(), orderId ->
                    new OrderDTO(item.getOrderId(), item.getOrderDate(), item.getStatus(), new ArrayList<>())
            ).getOrderItems().add(new OrderItemDTO(item.getOrderItemId(), item.getProductCode(), item.getProductName(), item.getQuantity()));
        }
        return new ArrayList<>(orderMap.values());
    }
}

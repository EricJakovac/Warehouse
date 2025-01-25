package com.backend.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private String status;
    private List<OrderItemDTO> orderItems;
}

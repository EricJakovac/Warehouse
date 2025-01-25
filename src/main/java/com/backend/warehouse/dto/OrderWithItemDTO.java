package com.backend.warehouse.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderWithItemDTO {
    private Long orderId;
    private LocalDate orderDate;
    private String status;
    private Long orderItemId;
    private String productCode;
    private String productName;
    private Integer quantity;
}

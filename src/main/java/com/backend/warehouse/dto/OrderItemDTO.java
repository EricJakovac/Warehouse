package com.backend.warehouse.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long orderItemId;
    private String productCode;
    private String productName;
    private Integer quantity;
}

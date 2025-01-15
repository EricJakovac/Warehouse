package com.backend.warehouse.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductRequest {
    private String productName;
    private String productCode;
    private Integer productQuantity;
    private Integer productMinQuantity;
    private Double productPrice;
    private LocalDateTime productArriveDate;
    private LocalDateTime productDepartureDate;
    private Long warehouseId;
}


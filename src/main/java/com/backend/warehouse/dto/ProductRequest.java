package com.backend.warehouse.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductRequest {
    private String productName;
    private String productCode;
    private Integer productQuantity;
    private Integer productMinQuantity;
    private Double productPrice;
    private LocalDate productArriveDate;
    private LocalDate productDepartureDate;
    private Long warehouseId;
}


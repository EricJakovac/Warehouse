package com.backend.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @NotNull(message = "Količina proizvoda je obavezna.")
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonBackReference
    private WarehouseOrder warehouseOrder;
    
    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "product_code")
    @JsonIgnore
    private Product product;
    
}

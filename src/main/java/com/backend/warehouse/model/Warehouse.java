package com.backend.warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long warehouseId;

    @NotBlank(message = "Naziv skladišta je obavezan.")
    private String warehouseName;

    @NotBlank(message = "Lokacija skladišta je obavezna.")
    private String location;

    @NotNull
    private Boolean active;

    //CascadeType.ALL znači da će se automatski primjeniti operacije poput
    // save, delete, update na sve povezane Product entitete
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

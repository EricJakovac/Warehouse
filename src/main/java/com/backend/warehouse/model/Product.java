package com.backend.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//Postavljeni indeksi za fazu Automatizaciju narudžbi robe
/*@Table(
        indexes = {
                @Index(name = "indexWarehouseId", columnList = "warehouseId"),
                @Index(name = "indexProductQuantity", columnList = "productQuantity")
        })*/
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Naziv proizvoda je obavezan.")
    private String productName;

    @NotBlank(message = "Šifra proizvoda je obavezna.")
    private String productCode;

    @NotNull(message = "Količina proizvoda je obavezna.")
    private Integer productQuantity;

    @NotNull(message = "Minimalna količina proizvoda je obavezna.")
    private Integer productMinQuantity;

    private Double productPrice;

    //Dolazak proizvoda
    @NotNull(message = "Datum dolaska proizvoda je obavezan.")
    @Column(nullable = false)
    private LocalDate productArriveDate;

    //Odlazak proizvoda
    @NotNull(message = "Datum odlaska proizvoda je obavezan.")
    @Column(nullable = false)
    private LocalDate productDepartureDate;

    @ManyToOne
    @JoinColumn(name = "warehouseId", nullable = false)
    @JsonBackReference
    private Warehouse warehouse;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductMinQuantity() {
        return productMinQuantity;
    }

    public void setProductMinQuantity(Integer productMinQuantity) {
        this.productMinQuantity = productMinQuantity;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public LocalDate getProductArriveDate() {
        return productArriveDate;
    }

    public void setProductArriveDate(LocalDate productArriveDate) {
        this.productArriveDate = productArriveDate;
    }

    public LocalDate getProductDepartureDate() {
        return productDepartureDate;
    }

    public void setProductDepartureDate(LocalDate productDepartureDate) {
        this.productDepartureDate = productDepartureDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}

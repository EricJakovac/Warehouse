package com.backend.warehouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        indexes = {
                @Index(name = "indexProductQuantityMinimum", columnList = "product_code, product_quantity, product_min_quantity")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Naziv proizvoda je obavezan.")
    private String productName;

    @NotBlank(message = "Šifra proizvoda je obavezna.")
    @Column(name = "product_code", unique = true)
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

}

package com.backend.warehouse.event;

import com.backend.warehouse.model.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductQuantityUpdateEvent extends ApplicationEvent {

    private final Product product;

    public ProductQuantityUpdateEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

}

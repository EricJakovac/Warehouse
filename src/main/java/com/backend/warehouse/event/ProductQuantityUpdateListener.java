package com.backend.warehouse.event;

import com.backend.warehouse.model.Product;
import com.backend.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQuantityUpdateListener {

    private final OrderService orderService;

    @Autowired
    public ProductQuantityUpdateListener (OrderService orderService){
        this.orderService = orderService;
    }

    @EventListener
    public void onProductQuantityUpdate(ProductQuantityUpdateEvent productQuantityUpdateEvent) {
        Product product = productQuantityUpdateEvent.getProduct();
        System.out.println("Listener aktiviran za proizvod: " + product.getProductName());

        if (product.getProductQuantity() <= product.getProductMinQuantity()) {
            int orderQuantity = (product.getProductMinQuantity() * 2) - product.getProductQuantity();

            if (orderQuantity > 0) {
                orderService.createOrderForProduct(product, orderQuantity);
                System.out.println("Narudžba kreirana za proizvod: " + product.getProductName());
            }
        }
    }

}

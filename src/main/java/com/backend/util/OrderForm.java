package com.backend.util;

import com.backend.datatransferobjects.OrderProductDto;

import java.util.List;

/**
 * Klasse die festlegt wie eine Post-Request aussehen muss (siehe OrderController)
 */
public class OrderForm {

    //Liste mit Produkten und deren Quantitäten/Mengen
    private List<OrderProductDto> productOrders;

    public List<OrderProductDto> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<OrderProductDto> productOrders) {
        this.productOrders = productOrders;
    }
}

package com.backend.datatransferobjects;

import com.backend.model.Product;

/**
 * Klasse, die ein Produkt und dessen Anzahl enthält
 */
public class OrderProductDto {

    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

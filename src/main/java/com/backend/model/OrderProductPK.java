package com.backend.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Klasse die den PK (die beiden PK's) der Verknüpfungstabelle darstellt
 * @Embeddable Diese Klasse kann nicht unabhängig existieren, nur Embedded
 */
@Embeddable
public class OrderProductPK implements Serializable {

    /**
     * @ManyToOne legt fest, dass es eine n zu 1 beziehung besteht
     * @JoinColumn führt einen join aus und definiert den Namen der Spalte
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * @ManyToOne legt fest, dass es eine n zu 1 beziehung besteht
     * @JoinColumn führt einen join aus und definiert den Namen der Spalte
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Klasse, die die Verknüpfungstabelle zwischen Order und Product darstellt (2 PK/FK ID, + QUantity)
 * @Entity Klasse stellt Tabelle in der DB dar
 */
@Entity
public class OrderProduct {

    /**
     * @EmbeddedID Die PK's dieser Klasse sind in einer weiteren Klasse definiert, diese wird hier referenziert.
     *             Man kann nicht 2 PK's zu einer Klasse zuordnen
     * @JsonIgnore Nicht relevant für DB
     */
    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    /**
     * @Column tag legt fest das persistiert wird. Außerdem darf quantity nicht null sein.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Konstruktor
     * @param order Die Order, die zu dieser Bestellung gehört
     * @param product Das Produkt, dass zu dieser Bestellung gehört
     * @param quantity Die Menge des Produkts
     */
    public OrderProduct(Order order, Product product, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    /**
     * Default Konstruktor
     */
    public OrderProduct() {
    }

    /**
     * Ruft die getProduct() Methode der EmbeddedID Klasse auf
     * @return
     */
    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    /**
     * Ruft die getTotalPrice() Methode der EmbeddedID Klasse auf
     * @return
     */
    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public OrderProductPK getPk() {
        return pk;
    }

    public void setPk(OrderProductPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

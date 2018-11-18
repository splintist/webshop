package com.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Klasse die ein Produkt modelliert.
 * @Entity stellt eine DB Tabelle dar.
 */
@Entity
public class Product {

    /**
     * @ID ist Primary Key
     * @GeneratedValue wird generiert nach Schmea IDENTITY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String pictureUrl;

    private String description;

    /**
     * Konstruktor
     * @param id ID des Produkts
     * @param name Name des Produkts
     * @param price Preis des Produkts
     * @param pictureUrl Bild des Produkts
     * @param description Beschreibung des Produkts
     */
    public Product(Long id, String name, Double price, String pictureUrl, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.description = description;
    }

    /**
     * Default Konstruktor
     */
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

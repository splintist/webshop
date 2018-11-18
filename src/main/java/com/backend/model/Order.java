package com.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO: warum will jpa keine table mit default name? Debuggen DONE
//JPA-H2 DB zuweisung/erstellen: https://spring.io/guides/gs/accessing-data-jpa/

/**
 * Modell einer Order/Bestellung/Purchase
 * @Entity Lägt fest, dass diese Klasse eine JPA/H2 Entity ist
 * @Table Legt den Namen der Tabelle in der Datenbank fest. Wenn dieser Tag fehlt wird der Klassenname genommen.
 *        ORDER ist allerdings kein gültiger Name, deswegen muss ein anderer Tabellenname genommen werden
 */
@Entity
@Table(name = "bestellung")
public class Order {

    /**
     * @Id Dieser Tag legt fest, dass es der Primary Key ID in der DB ist
     * @GeneratedValue Dieser Wert wird nach dem Schema IDENTITY generiert
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    /**
     * @OneToMany 1 zu N beziehung wird hier definiert. DB wird mitgeteilt, dass hier eine Verbindungstabelle
     * benötigt wird. Fremdschlüssel ist der PK (ID) der order Klasse.
     * @Valid "SpringMagic" Spring validiert, dass orderProducts valid ist.
     * @Transient Wird nicht in der Datenbank persistiert
     */
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    /**
     * Funktion, die alle Preise aufsummiert und den kompletten Preis zurückliefert.
     * @Transient Tag legt fest, dass dies kein Feld in der DB ist. Nicht persistiert wird.
     * @return Den kompletten Preis einer Order.
     */
    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts) {
            sum += op.getTotalPrice();
        }

        return sum;
    }

    /**
     * Funktion, die die Anzahl der Produkte einer Order zurückgibt
     * @Transient Wird nicht in der Datenbank persistiert
     * @return Anzahl der Produkte
     */
    @Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

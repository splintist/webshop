package com.backend.service;

import com.backend.model.Order;
import com.backend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Klasse die, die Datenbankkommunikation für die Verknüpfungstabelle übernimmt
 * @Service Deklariert diese Klasse als Service
 * @Transactional Klasse übernimmt Transaktionsaufgaben mit der DB. Spring erledigt großteil
 * http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
 */
@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    /**
     * Konstruktor
     * @param orderRepository Verwendete DB-Tabelle
     */
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Liefert alle Bestellungen zurück
     * @return Alle Bestellung als Iterable liste (for-each)
     */
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    /**
     * Fügt eine Order mit dem aktuellen Datum zur DB hinzu
     * @param order Die Order die angelegt werden soll
     * @return
     */
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

    /**
     * Updated eine bestehende Order
     * @param order
     */
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}

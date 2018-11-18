package com.backend.service;

import com.backend.model.OrderProduct;
import com.backend.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Klasse die, die Datenbankkommunikation für die Verknüpfungstabelle übernimmt
 * @Service Deklariert diese Klasse als Service
 * @Transactional Klasse übernimmt Transaktionsaufgaben mit der DB. Spring erledigt großteil
 * http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
 */
@Service
@Transactional
public class OrderProductService {

    private OrderProductRepository orderProductRepository;

    /**
     * Konstruktor
     * @param orderProductRepository Verwendete DB-Tabelle
     */
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    /**
     * Fügt eine neue Order-Prudukt Kombi zur DB hinzu
     * @param orderProduct Enthält eine Order und die dazugehörigen Produkte
     * @return
     */
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}

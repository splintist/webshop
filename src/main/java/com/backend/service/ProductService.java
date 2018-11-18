package com.backend.service;

import com.backend.model.Product;
import com.backend.repository.ProductRepository;
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
public class ProductService {

    private ProductRepository productRepository;

    /**
     * Konstruktor
     * @param productRepository Verwendete DB-Tabelle
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Liefert alle Produkte als Iterable zurück (for-each)
     * @return Alle Produkte als Iterable
     */
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Liefert ein Produkt anhand einer übergebenen ID zurück
     * @param id Die ID des Produkts (PK)
     * @return Das Produkt
     * @throws RuntimeException Wird geworfen wenn kein Produkt mit dieser ID existiert
     */
    public Product getProduct(long id) {
        return productRepository
          .findById(id)
          .orElseThrow(() -> new RuntimeException("Produkt existiert nicht"));
    }

    /**
     * Fügt ein Produkt zur DB hinzu
     * @param product Das Produkt was hinzugefügt werden soll
     * @return
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }
}

package com.backend.controller;

import com.backend.model.Product;
import com.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @RestController Definiert, dass hier ein RestController ist
 * @CrossOrigin Erlaubt Zugriff auf den Controller von anderen Quellen (nicht localhost)
 * @RequestMapping Unter der url, die hier definiert ist, ist die Schnittstelle abrufbar
 */
@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    /**
     * Konstruktor
     * @param productService Schnittstelle zur Datenbank
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Methode die alle Produkte aus der Datenbank abruft und als Iterable zurückgibt
     * Typ Iterable erlaubt das durchlaufen aller Produkte mit for-each-loop
     * @return Iterable, das alle Produkte enthält
     * @GetMapping Bei einem Get-Request mit "" oder "/" wird diese Methode aufgerufen
     * @ResponseStatus(HttpStatus.OK) Liefert den HTTP-Status OK zurück
     */
    @GetMapping(value = { "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}

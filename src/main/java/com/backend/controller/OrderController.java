package com.backend.controller;

import com.backend.datatransferobjects.OrderProductDto;
import com.backend.model.Order;
import com.backend.model.OrderProduct;
import com.backend.model.OrderStatus;
import com.backend.service.*;
import com.backend.util.OrderForm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @RestController Definiert, dass hier ein RestController ist
 * @CrossOrigin Erlaubt Zugriff auf den Controller von anderen Quellen (nicht localhost)
 * @RequestMapping Unter der url, die hier definiert ist, ist die Schnittstelle abrufbar
 */
@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;
    private ProductService productService;
    private OrderProductService orderProductService;

    /**
     * Konstruktor
     * @param productService Übernimmt Kommunikation mit der Datenbank
     * @param orderService Übernimmt Kommunikation mit der Datenbank
     * @param orderProductService Übernimmt Kommunikation mit der Datenbank
     */
    public OrderController(ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    /**
     * Methode, die alle Orders/Bestellungen aus der Datenbank abruft und zurückgibt
     * @return Alle Orders
     * @GetMapping Bei einem Get-Request mit "" oder "/" wird diese Methode aufgerufen
     * @ResponseStatus(HttpStatus.OK) Liefert den HTTP-Status OK zurück
     */
    @GetMapping (value = { "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    /**
     * Methode, die aus einer durch eine Post-request übergebene OrderForm eine neue interne Order erstellt
     * und anschließend eine ResponseEntity(HttpEntity) zurückgibt.
     * @param form Definiert die Form des Post-Inhalts. Diese muss vom Typ OrderForm(Klasse) sein.
     * @return ResponseEntity extended eine HttpEntity (Http Request oder Reponse), fügt noch einen HTTPStatusCode hinzu
     * @PostMapping Bei einem Post-request mit "" oder "/" auf die Controller URL wird diese Methode aufgerufen
     * @RequestBody Der Inhalt des Post-requests muss die Form, die in der Klasse OrderForm definiert ist haben
     */
    @PostMapping (value = { "", "/" })
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        //Erstellen einer Liste mit allen der Order zugeordneten Produkte und deren Mengen
        List<OrderProductDto> formDtos = form.getProductOrders();
        //Verifizieren, dass Produkte existieren
        validateProductsExistence(formDtos);
        //Erstellen einer neuen Order und anlegen in der Datenbank
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        //Iteriert durch alle Produkte der übergebenen OrderForm und fügt diese mit Quantität zur lokalen Liste hinzu
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        //Aktualisieren der Datenbank
        this.orderService.update(order);

        //Erstellen der HTTP-Daten um ResponseEntity zurückzugeben
        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    /**
     * Methode die sicherstellt das aus einer Liste aus Produkten alle Produkte wirklich existieren
     * @param orderProducts Liste von Produkten mit Quantität/Menge
     */
    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());
        //TODO: Exception falls nicht existent (ProductNotFoundException)
    }
}

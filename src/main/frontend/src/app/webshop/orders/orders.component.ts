import {Component, OnInit} from '@angular/core';
import {ProductOrders} from "../models/product-orders.model";
import {Subscription} from "rxjs/internal/Subscription";
import {WebshopService} from "../services/WebshopService";

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
    orders: ProductOrders;
    total: number;
    paid: boolean;
    sub: Subscription;

    /**
     * Konstruktor
     * @param webshopService Service, der mit der API kommuniziert
     */
    constructor(private webshopService: WebshopService) {
        this.orders = this.webshopService.ProductOrders;
    }

    ngOnInit() {
        this.paid = false;
        this.sub = this.webshopService.OrdersChanged.subscribe(() => {
            this.orders = this.webshopService.ProductOrders;
        });
        this.loadTotal();
    }

    /**
     * Funktion, die eine Bestellung abschließt
     */
    pay() {
        this.paid = true;
        this.webshopService.saveOrder(this.orders).subscribe();
    }

    /**
     * Läd den aufsummierten Preis der Produkte der Bestellung
     */
    loadTotal() {
        this.sub = this.webshopService.TotalChanged.subscribe(() => {
            this.total = this.webshopService.Total;
        });
    }
}

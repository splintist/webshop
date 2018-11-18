import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {CartComponent} from "./cart/cart.component";
import {OrdersComponent} from "./orders/orders.component";
import {WebshopService} from "./services/WebshopService";

@Component({
    selector: 'app-webshop',
    templateUrl: './webshop.component.html',
    styleUrls: ['./webshop.component.css']
})
export class WebshopComponent implements OnInit {

    fullOrdersUrl;
    fullProductsUrl;
    fullApiDocsUrl;

    private collapsed = true;
    orderFinished = false;

    /**
     * @ViewChild läd die ProductsComponent wenn sie benötigt wird (Referenz auf productsComponent)
     */
    @ViewChild('productsC')
    productsC: ProductsComponent;

    /**
     * @ViewChild läd die CartComponent wenn sie benötigt wird (Referenz auf cartComponent)
     */
    @ViewChild('cartC')
    cartC: CartComponent;

    /**
     * @ViewChild läd die OrdersComponent wenn sie benötigt wird (Referenz auf ordersComponent)
     */
    @ViewChild('ordersC')
    ordersC: OrdersComponent;

    /**
     * Konstruktor
     * @param webshopService Service der mit der Api kommuniziert
     */
    constructor(private webshopService: WebshopService) {
        this.fullOrdersUrl = webshopService.fullOrdersUrl;
        this.fullProductsUrl = webshopService.fullProductsUrl;
        this.fullApiDocsUrl = webshopService.fullApiDocsUrl;
    }

    ngOnInit() {
    }

    /**
     * Toggled das collapsen der navbar auf mobilgeräten
     */
    toggleCollapsed(): void {
        this.collapsed = !this.collapsed;
    }

    /**
     * Toggled lokale variable orderFinished (eigentlich ein setter)
     * @param orderFinished Boolean, der angibt ob die Order fertig ist.
     */
    finishOrder(orderFinished: boolean) {
        this.orderFinished = orderFinished;
    }

    /**
     * Funktion zum resetten des shops. Wird z.B. nach abschluss der Bestellung gecalled
     */
    reset() {
        this.orderFinished = false;
        this.productsC.reset();
        this.cartC.reset();
        this.ordersC.paid = false;
    }
}

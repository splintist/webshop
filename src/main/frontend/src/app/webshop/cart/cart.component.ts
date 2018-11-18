import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {ProductOrders} from "../models/product-orders.model";
import {ProductOrder} from "../models/product-order.model";
import {WebshopService} from "../services/WebshopService";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnDestroy {
    orderFinished: boolean;
    orders: ProductOrders;
    total: number;
    sub: Subscription;

    /**
     * onOrderFinished wird durch den @Output() tag nach außen freigegeben. (Emitted)
     * 
     */
    @Output() onOrderFinished: EventEmitter<boolean>;

    /**
     * Konstruktor
     * Grund: https://stackoverflow.com/questions/35763730/difference-between-constructor-and-ngoninit/
     * 45430181#45430181
     * @param webshopService Der Webshopservice kommuniziert mit der API (Backend)
     */
    constructor(private webshopService: WebshopService) {
        this.total = 0;
        this.orderFinished = false;
        this.onOrderFinished = new EventEmitter<boolean>();
    }

    ngOnInit() {
        this.orders = new ProductOrders(); //Produkte mit Menge
        this.loadCart();
        this.loadTotal();
    }

    /**
     * Summiert den Preis aller Produkte im Warenkorb
     * @param products Alle Produkte im Warenkorb
     * @return Menge als number
     */
    private calculateTotal(products: ProductOrder[]): number {
        let sum = 0;
        products.forEach(value => {
            sum += (value.product.price * value.quantity);
        });
        return sum;
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    /**
     * Methode, die eine Bestellung abschließt.
     */
    finishOrder() {
        this.orderFinished = true;
        this.webshopService.Total = this.total;
        this.onOrderFinished.emit(this.orderFinished);
    }

    /**
     * Weißt dem Attribut total den aktuellen Wert aller Produkte im Warenkorb zu.
     */
    loadTotal() {
        this.sub = this.webshopService.OrdersChanged.subscribe(() => {
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }

    /**
     * Läd den aktuellen Cart Inhalt und subscribed auf changes in der Products Komponente,
     * um automatisch zu updaten
     */
    loadCart() {
        this.sub = this.webshopService.ProductOrderChanged.subscribe(() => {
            let productOrder = this.webshopService.SelectedProductOrder;
            if (productOrder) {
                this.orders.productOrders.push(new ProductOrder(
                    productOrder.product, productOrder.quantity));
            }
            this.webshopService.ProductOrders = this.orders;
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }

    /**
     * Resetted die Warenkorb Komponente
     */
    reset() {
        this.orderFinished = false;
        this.orders = new ProductOrders();
        this.orders.productOrders = []
        this.loadTotal();
        this.total = 0;
    }
}

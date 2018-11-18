import {Component, OnInit} from '@angular/core';
import {ProductOrder} from "../models/product-order.model";
import {WebshopService} from "../services/WebshopService";
import {Subscription} from "rxjs/internal/Subscription";
import {ProductOrders} from "../models/product-orders.model";
import {Product} from "../models/product.model";

@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
    productOrders: ProductOrder[] = [];
    products: Product[] = [];
    selectedProductOrder: ProductOrder;
    private shoppingCartOrders: ProductOrders;
    sub: Subscription;
    productSelected: boolean = false;

    /**
     * Konstruktor
     * @param webshopService Service der mit der Api kommuniziert
     */
    constructor(private webshopService: WebshopService) {
    }

    ngOnInit() {
        this.productOrders = [];
        this.loadProducts();
        this.loadOrders();
    }

    /**
     * Fügt das aktuell ausgewählte Produkt mit der Anzahl zum Warenkorb hinzu
     * @param productOrder Produkt mit Anzahl
     */
    addToCart(productOrder: ProductOrder) {
        this.webshopService.SelectedProductOrder = productOrder;
        this.selectedProductOrder = this.webshopService.SelectedProductOrder;
        this.productSelected = true;
    }

    /**
     * Entfernt das ausgewählte Produkt aus dem Warenkorb
     * @param productOrder
     */
    removeFromCart(productOrder: ProductOrder) {
        let index = this.getProductIndex(productOrder.product);
        if (index >= 0) {
            this.shoppingCartOrders.productOrders.splice(
                this.getProductIndex(productOrder.product), 1);
        }
        this.webshopService.ProductOrders = this.shoppingCartOrders;
        this.productSelected = false;
    }

    /**
     * Findet den Produktindex in der Bestellung anhand des übergebenen Produktes
     * @param product Das Produkt zu dem der Index gefunden werden soll
     * @return Der Index in der Bestellung
     */
    getProductIndex(product: Product): number {
        return this.webshopService.ProductOrders.productOrders.findIndex(
            value => value.product === product);
    }

    /**
     * Gibt zurück ob das als Parameter übergebene Produkt gerade ausgewählt ist
     * @param product
     */
    isProductSelected(product: Product): boolean {
        return this.getProductIndex(product) > -1;
    }

    /**
     * Läd die Produkte aus dem Backend mit Hilfe des webshop Services
     */
    loadProducts() {
        this.webshopService.getAllProducts()
            .subscribe(
                (products: any[]) => {
                    this.products = products;
                    this.products.forEach(product => {
                        this.productOrders.push(new ProductOrder(product, 0));
                    })
                },
                (error) => console.log(error)
            );
    }

    /**
     * Läd die Orders (Produkte+Menge)
     */
    loadOrders() {
        this.sub = this.webshopService.OrdersChanged.subscribe(() => {
            this.shoppingCartOrders = this.webshopService.ProductOrders;
        });
    }

    /**
     * Resetted die Products-Komponente
     */
    reset() {
        this.productOrders = [];
        this.loadProducts();
        this.webshopService.ProductOrders.productOrders = [];
        this.loadOrders();
        this.productSelected = false;
    }
}

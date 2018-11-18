import {ProductOrder} from "../models/product-order.model";
import {Subject} from "rxjs/internal/Subject";
import {ProductOrders} from "../models/product-orders.model";
import {HttpClient} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {environment} from "../../../environments/environment";

@Injectable()
export class WebshopService {

    private _productsUrl = environment.proxy + "/api/products";
    private _ordersUrl = environment.proxy + "/api/orders";

    private _fullProductsUrl = "<a class=\"nav-link\" href=\"" + this._productsUrl + "\">API Products</a>";
    private _fullOrdersUrl = "<a class=\"nav-link\" href=\"" + this._ordersUrl + "\">API Orders</a>";

    private productOrder: ProductOrder;
    private orders: ProductOrders = new ProductOrders();

    /**
     * Subjects funktionieren wie EventEmitters, teilen also allen Komponenten, die subscribed sind,
     * mit falls sich etwas ändert
     */
    private productOrderSubject = new Subject();
    private ordersSubject = new Subject();
    private totalSubject = new Subject();

    private total: number;

    /**
     * Subjects werden jetzt observiert/beobachtet
     */
    ProductOrderChanged = this.productOrderSubject.asObservable();
    OrdersChanged = this.ordersSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();

    /**
     * Konstruktor
     * @param http mit dem httpClient werden Get und post-requests gemacht
     */
    constructor(private http: HttpClient) {
    }

    /**
     * Ruft die api auf und liefert alle Produkte zurück
     */
    getAllProducts() {
        return this.http.get(this._productsUrl);
    }

    /**
     * Ruft die Api auf und saved eine Order/bestellung
     * @param productOrders die Bestellung
     */
    saveOrder(productOrders: ProductOrders) {
        return this.http.post(this._ordersUrl, productOrders);
    }

    get fullOrdersUrl(): string {
        return this._fullOrdersUrl;
    }
    get fullProductsUrl(): string {
        return this._fullProductsUrl;
    }

    /**
     * Setzt die aktuell ausgewählte ProductOrder auf die übergebene
     * @param value übergebene Bestellung
     * @constructor
     */
    set SelectedProductOrder(value: ProductOrder) {
        this.productOrder = value;
        this.productOrderSubject.next(); //.next() gibt das ganze an alle die subscribed sind (eventemitter)
    }

    /**
     * Gibt die aktuell ausgewählte ProductOrder zurück
     */
    get SelectedProductOrder() {
        return this.productOrder;
    }

    /**
     * Setzt die aktuellen ProductOrders auf den übergebenen Parameter
     * @param value Bestellung
     */
    set ProductOrders(value: ProductOrders) {
        this.orders = value;
        this.ordersSubject.next(); //.next() gibt das ganze an alle die subscribed sind (eventemitter)
    }

    get ProductOrders() {
        return this.orders;
    }

    get Total() {
        return this.total;
    }

    /**
     * Setzt die aktuelle Summe auf die übergebene Value
     * @param value Summe aller Artikel
     */
    set Total(value: number) {
        this.total = value;
        this.totalSubject.next(); //.next() gibt das ganze an alle die subscribed sind (eventemitter)
    }
}

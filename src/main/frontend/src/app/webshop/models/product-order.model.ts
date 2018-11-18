import {Product} from "./product.model";

/**
 * Modelliert eine Bestellung auf ein Produkt (Produkt+Anzahl)
 */
export class ProductOrder {
    product: Product;
    quantity: number;

    constructor(product: Product, quantity: number) {
        this.product = product;
        this.quantity = quantity;
    }
}

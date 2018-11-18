/**
 * Modelliert ein Produkt
 */
export class Product {
    id: number;
    name: string;
    price: number;
    pictureUrl: string;
    description: string;

    constructor(id: number, name: string, price: number, pictureUrl: string, description: string) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.description = description;
    }
}

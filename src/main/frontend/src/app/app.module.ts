import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {WebshopComponent} from './webshop/webshop.component';
import {ProductsComponent} from './webshop/products/products.component';
import {CartComponent} from './webshop/cart/cart.component';
import {OrdersComponent} from './webshop/orders/orders.component';
import {WebshopService} from "./webshop/services/WebshopService";

@NgModule({
    declarations: [
        AppComponent,
        WebshopComponent,
        ProductsComponent,
        CartComponent,
        OrdersComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [WebshopService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

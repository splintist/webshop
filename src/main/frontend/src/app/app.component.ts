import {Component} from '@angular/core';
import {WebshopService} from "./webshop/services/WebshopService";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [WebshopService]
})
export class AppComponent {

}

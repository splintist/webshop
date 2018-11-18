package com.backend;

import com.backend.model.Product;
import com.backend.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebshopApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(WebshopApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {
        return args -> {
            productService.save(new Product(1L, "Fernseher", 600.00,
                    "https://i.otto.de/i/otto/24503296/telefunken-l22f275m4-led-fernseher-56-cm-22-zoll-full-hd-schwarz.jpg?$formatz$",
                    "Ein toller Fernseher"));
            productService.save(new Product(2L, "Laptop", 1200.00,
                    "http://install.mein-phone-shop.de/270-large_default/macbook.jpg",
                    "neuer Laptop"));
            productService.save(new Product(3L, "PS4 Pro", 350.00,
                    "https://cdn1.alphr.com/sites/alphr/files/styles/insert_main_wide_image/public/2016/11/ps4_pro_review_-_playstation_4_pro_9.jpg?itok=gGCXGgiU",
                    "was letzte preis"));
            productService.save(new Product(4L, "Big Mac", 3.55,
                    "https://cdn.newsapi.com.au/image/v1/e10debce170fd18a27f0fd3f024ec81e",
                    "Der Big Mac, in Deutschland ehemals auch als Big Mäc bezeichnet, ist eine doppelstöckige Cheeseburger-Variante des Fast-Food-Konzerns McDonald’s."));
            productService.save(new Product(5L, "Capri Sun", 00.35,
                    "https://www.edhaas.at/sites/default/files/styles/colorbox_big/public/produkte/caprisun_orange_multivitamin.png?itok=FLovRblR",
                    "Capri-Sun ist ein Fruchtsaftgetränk ohne Kohlensäure, das 1969 zunächst in den Geschmacksrichtungen Orange und Zitrone auf den Markt kam und in einem Standbodenbeutel à 200 Milliliter mit Trinkhalm vertrieben wird"));
            productService.save(new Product(6L, "Croisson", 00.22,
                    "http://www.cornwallcountrymarket.com/wp-content/uploads/2017/05/Croissant.jpg",
                    "von Netto"));
            productService.save(new Product(7L, "Roley", 11500.00,
                    "https://chronextcms.imgix.net/top_brands/new_rolex_start.jpg",
                    "nur für Splasher"));
            productService.save(new Product(8L, "BitCoin", 5600.00,
                    "https://www.bitcoinmag.de/wp-content/uploads/2018/06/bitcoin_trend.jpg",
                    "bitcoin"));
        };
    }
}

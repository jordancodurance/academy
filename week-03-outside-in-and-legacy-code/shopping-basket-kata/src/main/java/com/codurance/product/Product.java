package com.codurance.product;

import java.util.UUID;

public class Product {

    public final UUID id;
    public final String name;
    public final double price;
    public final ProductType type;

    public Product(UUID id, String name, double price, ProductType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

}

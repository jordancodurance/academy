package com.codurance.basket;

public class BasketItem {

    public final int quantity;
    public final String name;
    public final double price;

    public BasketItem(int quantity, String name, double price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public Double calculateTotal() {
        return price * quantity;
    }

}

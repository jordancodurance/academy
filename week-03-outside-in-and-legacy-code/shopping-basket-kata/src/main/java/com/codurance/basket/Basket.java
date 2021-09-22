package com.codurance.basket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Basket {

    public final LocalDateTime createdOn;
    public final List<BasketItem> items;

    public Basket(LocalDateTime createdOn, List<BasketItem> items) {
        this.createdOn = createdOn;
        this.items = items;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = ofPattern("dd/MM/yyyy");

        return createdOn.format(formatter);
    }

    public double calculateTotal() {
        return items
                .stream()
                .mapToDouble(BasketItem::calculateTotal)
                .sum();
    }

}

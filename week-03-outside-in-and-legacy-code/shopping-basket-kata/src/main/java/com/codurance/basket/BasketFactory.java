package com.codurance.basket;

import com.codurance.time.TimestampProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BasketFactory {

    private final TimestampProvider timestampProvider;

    public BasketFactory(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public Basket createNewBasket() {
        LocalDateTime createdOn = timestampProvider.now();
        List<BasketItem> items = new ArrayList<>();

        return new Basket(createdOn, items);
    }

}

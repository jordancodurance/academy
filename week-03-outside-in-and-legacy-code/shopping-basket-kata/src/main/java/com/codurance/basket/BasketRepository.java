package com.codurance.basket;

import com.codurance.time.TimestampProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BasketRepository {

    private final TimestampProvider timestampProvider;

    public BasketRepository(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    private final HashMap<UUID, Basket> basketStore = new HashMap<>();

    public Basket findBasket(UUID userId) {
        return basketStore.getOrDefault(userId, createNewBasket());
    }

    private Basket createNewBasket() {
        LocalDateTime createdOn = timestampProvider.now();
        List<BasketItem> items = new ArrayList<>();

        return new Basket(createdOn, items);
    }

    public void addToBasket(UUID userId, BasketItem basketItem) {
        Basket basket = findBasket(userId);
        basket.items.add(basketItem);

        basketStore.put(userId, basket);
    }

}

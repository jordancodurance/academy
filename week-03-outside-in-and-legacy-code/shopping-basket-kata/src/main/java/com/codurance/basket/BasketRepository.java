package com.codurance.basket;

import java.util.HashMap;
import java.util.UUID;

public class BasketRepository {

    private final BasketFactory basketFactory;

    public BasketRepository(BasketFactory basketFactory) {
        this.basketFactory = basketFactory;
    }

    private final HashMap<UUID, Basket> basketStore = new HashMap<>();

    public Basket findBasket(UUID userId) {
        return basketStore.getOrDefault(userId, basketFactory.createNewBasket());
    }

    public void addToBasket(UUID userId, BasketItem basketItem) {
        Basket basket = findBasket(userId);
        basket.items.add(basketItem);

        basketStore.put(userId, basket);
    }

}

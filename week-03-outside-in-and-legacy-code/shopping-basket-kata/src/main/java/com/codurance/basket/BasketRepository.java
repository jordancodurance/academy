package com.codurance.basket;

import java.util.HashMap;
import java.util.UUID;

public class BasketRepository {

    private final BasketFactory basketFactory;

    private final HashMap<UUID, Basket> basketStore = new HashMap<>();

    public BasketRepository(BasketFactory basketFactory) {
        this.basketFactory = basketFactory;
    }

    public Basket getBasket(UUID userId) {
        Basket basket = basketStore.get(userId);
        if (basket == null) {
            basket = basketFactory.createNewBasket(userId);
            basketStore.put(userId, basket);
        }

        return basket;
    }

    public void addToBasket(UUID userId, BasketItem basketItem) {
        Basket basket = getBasket(userId);
        basket.items.add(basketItem);

        basketStore.put(userId, basket);
    }

}

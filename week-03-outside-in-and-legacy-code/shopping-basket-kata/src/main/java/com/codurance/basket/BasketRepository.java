package com.codurance.basket;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Logger.getLogger;

public class BasketRepository {

    private final Logger logger = getLogger(this.getClass().getName());
    private final BasketFactory basketFactory;

    private final HashMap<UUID, Basket> basketStore = new HashMap<>();

    public BasketRepository(BasketFactory basketFactory) {
        this.basketFactory = basketFactory;
    }

    public Basket findExistingBasket(UUID userId) {
        Basket basket = basketStore.get(userId);
        if (basket == null) throw new BasketNotFoundException();

        return basket;
    }

    public Basket createNewBasket(UUID userId) {
        Basket basket = basketFactory.createNewBasket();
        basketStore.put(userId, basket);
        logger.info(format("[BASKET CREATED]: Created[<%s>], User[%s]", basket.createdOn, userId));

        return basket;
    }

    public void updateBasket(UUID userId, Basket basket) {
        basketStore.put(userId, basket);
    }

}

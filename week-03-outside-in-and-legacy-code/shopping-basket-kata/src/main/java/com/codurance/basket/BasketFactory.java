package com.codurance.basket;

import com.codurance.time.TimestampProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Logger.getLogger;

public class BasketFactory {

    private final Logger logger = getLogger(this.getClass().getName());
    private final TimestampProvider timestampProvider;

    public BasketFactory(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public Basket createNewBasket(UUID userId) {
        LocalDateTime createdOn = timestampProvider.now();
        List<BasketItem> items = new ArrayList<>();

        logger.info(format("[BASKET CREATED]: Created[<%s>], User[%s]", createdOn, userId));
        return new Basket(createdOn, items);
    }

}

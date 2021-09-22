package com.codurance;

import com.codurance.basket.Basket;
import com.codurance.basket.BasketItem;
import com.codurance.basket.BasketNotFoundException;
import com.codurance.basket.BasketRepository;
import com.codurance.product.Product;
import com.codurance.product.ProductRepository;

import java.util.UUID;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;

public class ShoppingBasketService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public ShoppingBasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public void addItem(UUID userId, UUID productId, int quantity) {
        Product matchingProduct = productRepository.findProduct(productId);
        BasketItem item = new BasketItem(quantity, matchingProduct.name, matchingProduct.price);
        Basket basket = getBasket(userId);
        basket.items.add(item);

        basketRepository.updateBasket(userId, basket);
        logger.info(format("[ITEM ADDED TO SHOPPING CART]: Added[<%s>], User[%s], Product[%s], Quantity[%d], Price[<£%.2f>]", now(), userId, productId, quantity, matchingProduct.price));
    }

    private Basket getBasket(UUID userId) {
        try {
            return basketRepository.findExistingBasket(userId);
        } catch (BasketNotFoundException exception) {
            return basketRepository.createNewBasket(userId);
        }
    }

    public Basket basketFor(UUID userId) {
        return getBasket(userId);
    }

}

package com.codurance;

import com.codurance.basket.Basket;
import com.codurance.basket.BasketItem;
import com.codurance.basket.BasketRepository;
import com.codurance.product.Product;
import com.codurance.product.ProductRepository;

import java.util.UUID;

public class ShoppingBasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public ShoppingBasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public void addItem(UUID userId, UUID productId, int quantity) {
        Product matchingProduct = productRepository.findProduct(productId);
        BasketItem item = new BasketItem(quantity, matchingProduct.name, matchingProduct.price);

        basketRepository.addToBasket(userId, item);
    }

    public Basket basketFor(UUID userId) {
        return basketRepository.findBasket(userId);
    }

}

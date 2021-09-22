package com.codurance;

import com.codurance.basket.Basket;
import com.codurance.basket.BasketFactory;
import com.codurance.basket.BasketItem;
import com.codurance.basket.BasketRepository;
import com.codurance.product.ProductRepository;
import com.codurance.time.TimestampProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.List.of;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingBasketServiceShould {

    private final TimestampProvider timestampProvider = mock(TimestampProvider.class);
    private final BasketFactory basketFactory = new BasketFactory(timestampProvider);
    private final BasketRepository basketRepository = new BasketRepository(basketFactory);
    private final ProductRepository productRepository = new ProductRepository();

    private final ShoppingBasketService shoppingBasketService = new ShoppingBasketService(basketRepository, productRepository);

    @Test
    public void addItemsToShoppingBasket() {
        given(timestampProvider.now()).willReturn(LocalDateTime.of(2021, 9, 21, 12, 34));
        UUID userId = fromString("f62dc4a8-3701-4dfa-85cd-258b83607a84");
        UUID hobbitProductId = fromString("dface31e-badf-4df6-a32c-cd0d830b3027");
        UUID breakingBadProductId = fromString("826373df-8668-4daf-a87c-1717ee1327c6");
        shoppingBasketService.addItem(userId, hobbitProductId, 2);
        shoppingBasketService.addItem(userId, breakingBadProductId, 5);

        Basket basket = shoppingBasketService.basketFor(userId);

        assertBasketHasExpectedContent(basket);
    }

    private void assertBasketHasExpectedContent(Basket basket) {
        List<BasketItem> expectedItems = of(
                new BasketItem(2, "The Hobbit", 5.00),
                new BasketItem(5, "Breaking Bad", 7.00)
        );

        assertEquals("21/09/2021", basket.getFormattedDate());
        assertBasketItemsMatch(expectedItems, basket.items);
        assertEquals(45.00, basket.calculateTotal());
    }

    private void assertBasketItemsMatch(List<BasketItem> expected, List<BasketItem> actual) {
        for (int i = 0; i < actual.size() - 1; i++)
            hasBasketItem(expected.get(i), actual.get(i));
    }

    private void hasBasketItem(BasketItem expected, BasketItem actual) {
        assertEquals(expected.quantity, actual.quantity);
        assertEquals(expected.name, actual.name);
        assertEquals(expected.price, actual.price);
    }

}

package com.codurance.product;

import java.util.List;
import java.util.UUID;

import static com.codurance.product.ProductType.BOOK;
import static com.codurance.product.ProductType.DVD;
import static java.lang.String.format;
import static java.util.UUID.fromString;

public class ProductRepository {

    private final List<Product> products = List.of(
            new Product(fromString("60cdc9e7-3ecc-4359-a16a-9349c274cf17"), "Lord of the Rings", 10.00, BOOK),
            new Product(fromString("dface31e-badf-4df6-a32c-cd0d830b3027"), "The Hobbit", 5.00, BOOK),
            new Product(fromString("ced7c175-2a68-4419-8d6b-b1394037b957"), "Game of Thrones", 7.00, DVD),
            new Product(fromString("826373df-8668-4daf-a87c-1717ee1327c6"), "Breaking Bad", 7.00, DVD)
    );

    public Product findProduct(UUID productId) {
        return products
                .stream()
                .filter(product -> product.id.equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(format("Unable to find product with id: %s", productId.toString())));
    }

}

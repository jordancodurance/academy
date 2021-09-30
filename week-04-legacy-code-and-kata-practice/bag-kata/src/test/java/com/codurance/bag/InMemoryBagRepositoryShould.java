package com.codurance.bag;

import com.codurance.item.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryBagRepositoryShould {

    private final InMemoryBagRepository target = new InMemoryBagRepository();

    @Test
    void start_with_no_bags_available() {
        assertTrue(target.getBags().isEmpty());
    }

    @Test
    void add_categorised_bag_with_incremented_identifier() {
        BagIdentifier newBagIdentifier = target.addBag(MISCELLANEOUS, 5);

        Bag bag = target.getBags().get(0);
        assertEquals(1, newBagIdentifier.get());
        assertEquals(MISCELLANEOUS, bag.getItemCategory());
        assertEquals(emptyList(), bag.getContents());
        assertEquals(5, bag.getCapacity());
    }

    @Test
    void add_item_to_persisted_bag() {
        BagIdentifier identifier = new BagIdentifier(1);
        Item item = new Item("Leather", CLOTHES);
        target.addBag(MISCELLANEOUS, 8);

        target.addItem(identifier, item);

        List<Bag> bags = target.getBags();
        assertEquals(of("Leather"), bags.get(0).getContents());
    }

    @Test
    void replace_all_bags_persisted() {
        List<Bag> newBags = of(
                new Bag(new BagIdentifier(105), METALS, 10)
        );

        target.replaceBags(newBags);

        assertEquals(newBags, target.getBags());
    }

}
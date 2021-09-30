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

class InMemoryBagRepositoryShould {

    private final InMemoryBagRepository target = new InMemoryBagRepository();

    @Test
    void start_with_an_empty_backpack() {
        List<Bag> bags = target.getBags();

        Bag bag = bags.get(0);
        assertEquals(1, bags.size());
        assertEquals(100, bag.getIdentifier().get());
        assertEquals(MISCELLANEOUS, bag.getItemCategory());
        assertEquals(emptyList(), bag.getContents());
        assertEquals(8, bag.getCapacity());
    }

    @Test
    void add_item_to_persisted_bag() {
        BagIdentifier identifier = new BagIdentifier(100);
        List<Item> items = of(new Item("Leather", CLOTHES));

        target.addItems(identifier, items);

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

    @Test
    void add_categorised_extra_bag_with_incremented_identifier() {
        BagIdentifier newBagIdentifier = target.addExtraBag(METALS);

        Bag bag = target.getBags().get(1);
        assertEquals(101, newBagIdentifier.get());
        assertEquals(METALS, bag.getItemCategory());
        assertEquals(emptyList(), bag.getContents());
        assertEquals(4, bag.getCapacity());
    }

}
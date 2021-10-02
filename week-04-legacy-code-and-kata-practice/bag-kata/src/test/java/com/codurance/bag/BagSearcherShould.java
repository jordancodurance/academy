package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BagSearcherShould {

    private final BagSearcher target = new BagSearcher();

    @Test
    void find_categorised_bag_with_capacity_to_place_item_with_category_into() {
        Bag firstBag = createCategorisedBagWithCapacity(MISCELLANEOUS, 2, 1);
        Bag secondBag = createCategorisedBagWithCapacity(METALS, 2, 0);
        List<Bag> bags = of(firstBag, secondBag);

        Bag bagFound = target.findAvailableBagForItemReassignment(bags, METALS).get();

        assertEquals(secondBag, bagFound);
    }

    @Test
    void find_miscellaneous_bag_with_capacity_when_categorised_bag_is_full() {
        Bag firstBag = createCategorisedBagWithCapacity(MISCELLANEOUS, 2, 1);
        Bag secondBag = createCategorisedBagWithCapacity(METALS, 1, 1);
        List<Bag> bags = of(firstBag, secondBag);

        Bag bagFound = target.findAvailableBagForItemReassignment(bags, METALS).get();

        assertEquals(firstBag, bagFound);
    }

    @Test
    void find_miscellaneous_bag_with_capacity_when_no_categorised_bag_available() {
        Bag firstBag = createCategorisedBagWithCapacity(MISCELLANEOUS, 2, 1);
        Bag secondBag = createCategorisedBagWithCapacity(METALS, 2, 0);
        List<Bag> bags = of(firstBag, secondBag);

        Bag bagFound = target.findAvailableBagForItemReassignment(bags, CLOTHES).get();

        assertEquals(firstBag, bagFound);
    }

    @Test
    void find_no_bag_when_unable_to_find_available_categorised_bag_and_fallback_miscellaneous_bags_are_full() {
        Bag firstBag = createCategorisedBagWithCapacity(MISCELLANEOUS, 2, 2);
        Bag secondBag = createCategorisedBagWithCapacity(METALS, 2, 0);
        List<Bag> bags = of(firstBag, secondBag);

        Optional<Bag> bagFound = target.findAvailableBagForItemReassignment(bags, CLOTHES);

        assertTrue(bagFound.isEmpty());
    }

    private Bag createCategorisedBagWithCapacity(ItemCategory category, int maxCapacity, int currentCapacity) {
        int identifier = new Random().nextInt();
        Bag bag = new Bag(new BagIdentifier(identifier), category, maxCapacity);
        for (int i = 0; i < currentCapacity; i++) {
            bag.addItem(
                    new Item("Item name", category)
            );
        }
        return bag;
    }

}
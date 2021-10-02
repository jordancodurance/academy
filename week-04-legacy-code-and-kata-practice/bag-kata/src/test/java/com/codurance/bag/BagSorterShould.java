package com.codurance.bag;

import com.codurance.item.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.HERBS;
import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static com.codurance.item.ItemCategory.WEAPONS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BagSorterShould {

    private final BagSearcher bagSearcher = new BagSearcher();

    private final BagSorter target = new BagSorter(bagSearcher);

    @Test
    void organise_items_in_bags_by_category() {
        List<Bag> bags = createSortableBags();

        List<Bag> organisedBags = target.reorganiseBagsByCategory(bags);

        assertEquals(of("Linen", "Cherry Blossom", "Gold", "Leather"), organisedBags.get(0).getContents());
        assertEquals(of("Copper", "Copper", "Copper", "Iron"), organisedBags.get(1).getContents());
    }

    @Test
    void block_sorting_items_when_too_many_uncategorised_items_picked_up() {
        List<Bag> bags = createSortableBags();
        Bag metalBag = bags.get(0);
        metalBag.addItem(new Item("Mace", WEAPONS));
        metalBag.addItem(new Item("Dagger", WEAPONS));

        UnableToSortItemException exception = assertThrows(UnableToSortItemException.class, () -> target.reorganiseBagsByCategory(bags));

        assertEquals("Unable to sort item Leather due to insufficient storage", exception.getMessage());
    }

    @Test
    void organise_items_in_bags_alphabetically() {
        List<Bag> bags = createSortableBags();

        List<Bag> organisedBags = target.reorganiseBagsAlphabetically(bags);

        assertEquals(of("Cherry Blossom", "Copper", "Gold", "Iron", "Leather"), organisedBags.get(0).getContents());
        assertEquals(of("Copper", "Copper", "Linen"), organisedBags.get(1).getContents());
    }

    private List<Bag> createSortableBags() {
        Bag firstBag = createMiscellaneousBagFilledWithDifferentCategoryItems();
        Bag secondBag = createMetalBagFilledWithDifferentCategoryItems();
        return of(firstBag, secondBag);
    }

    private Bag createMiscellaneousBagFilledWithDifferentCategoryItems() {
        Bag bag = new Bag(new BagIdentifier(100), MISCELLANEOUS, 5);
        List<Item> items = of(
                new Item("Leather", CLOTHES),
                new Item("Gold", METALS),
                new Item("Cherry Blossom", HERBS),
                new Item("Iron", METALS),
                new Item("Copper", METALS)
        );

        for (Item item : items)
            bag.addItem(item);

        return bag;
    }

    private Bag createMetalBagFilledWithDifferentCategoryItems() {
        Bag bag = new Bag(new BagIdentifier(100), METALS, 4);
        List<Item> items = of(
                new Item("Linen", CLOTHES),
                new Item("Copper", METALS),
                new Item("Copper", METALS)
        );

        for (Item item : items)
            bag.addItem(item);

        return bag;
    }

}

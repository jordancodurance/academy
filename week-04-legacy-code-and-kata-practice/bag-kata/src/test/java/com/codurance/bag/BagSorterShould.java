package com.codurance.bag;

import com.codurance.item.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.HERBS;
import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BagSorterShould {

    private final BagSorter target = new BagSorter();

    @Test
    void organise_items_in_bags_by_category() {
        Bag firstBag = createBackpackFilledWithDifferentCategoryItems();
        Bag secondBag = createMetalBagFilledWithDifferentCategoryItems();
        List<Bag> bags = of(firstBag, secondBag);

        List<Bag> organisedBags = target.reorganiseBagsByCategory(bags);

        assertEquals(of("Linen", "Cherry Blossom", "Gold", "Leather"), organisedBags.get(0).getContents());
        assertEquals(of("Copper", "Copper", "Copper", "Iron"), organisedBags.get(1).getContents());
    }

    @Test
    void organise_items_in_bags_alphabetically() {
        Bag firstBag = createBackpackFilledWithDifferentCategoryItems();
        Bag secondBag = createMetalBagFilledWithDifferentCategoryItems();
        List<Bag> bags = of(firstBag, secondBag);

        List<Bag> organisedBags = target.reorganiseBagsAlphabetically(bags);

        assertEquals(of("Cherry Blossom", "Copper", "Gold", "Iron", "Leather"), organisedBags.get(0).getContents());
        assertEquals(of("Copper", "Copper", "Linen"), organisedBags.get(1).getContents());
    }

    private Bag createBackpackFilledWithDifferentCategoryItems() {
        Bag bag = new Bag(new BagIdentifier(100), MISCELLANEOUS, 8);
        List<Item> items = of(
                new Item("Leather", CLOTHES),
                new Item("Gold", METALS),
                new Item("Cherry Blossom", HERBS),
                new Item("Iron", METALS),
                new Item("Copper", METALS)
        );
        bag.addItems(items);

        return bag;
    }

    private Bag createMetalBagFilledWithDifferentCategoryItems() {
        Bag bag = new Bag(new BagIdentifier(100), METALS, 4);
        List<Item> items = of(
                new Item("Linen", CLOTHES),
                new Item("Copper", METALS),
                new Item("Copper", METALS)
        );
        bag.addItems(items);

        return bag;
    }

}

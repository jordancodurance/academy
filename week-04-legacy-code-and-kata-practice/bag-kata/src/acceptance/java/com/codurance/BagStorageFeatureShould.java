package com.codurance;

import com.codurance.bag.BagManager;
import com.codurance.bag.BagRepository;
import com.codurance.bag.BagSearcher;
import com.codurance.bag.BagSorter;
import com.codurance.bag.InMemoryBagRepository;
import com.codurance.item.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.HERBS;
import static com.codurance.item.ItemCategory.METALS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BagStorageFeatureShould {

    private final BagRepository bagRepository = new InMemoryBagRepository();
    private final BagManager bagManager = new BagManager(bagRepository);
    private final BagSearcher bagSearcher = new BagSearcher();
    private final BagSorter bagSorter = new BagSorter(bagSearcher);

    private final Adventurer adventurer = new Adventurer(bagManager, bagSorter);

    @Test
    void sort_items_alphabetically_within_bags_to_appropriate_category() {
        setUpAdventurerWithInitialItems();
        giveAdventurerTwoNewItems();

        adventurer.castOrganise();

        assertEquals(of("Cherry Blossom", "Iron", "Leather", "Marigold", "Silk", "Wool"), adventurer.getBags().get(0).getContents());
        assertEquals(of("Copper", "Copper", "Copper", "Gold"), adventurer.getBags().get(1).getContents());
    }

    private void setUpAdventurerWithInitialItems() {
        adventurer.startAdventure();
        List<Item> initialItems = of(
                new Item("Leather", CLOTHES),
                new Item("Iron", METALS),
                new Item("Copper", METALS),
                new Item("Marigold", HERBS),
                new Item("Wool", CLOTHES),
                new Item("Gold", METALS),
                new Item("Silk", CLOTHES),
                new Item("Copper", METALS)
        );
        adventurer.storeItems(initialItems);
    }

    private void giveAdventurerTwoNewItems() {
        List<Item> newItems = of(
                new Item("Copper", METALS),
                new Item("Cherry Blossom", HERBS)
        );
        adventurer.storeItems(newItems);
    }

}

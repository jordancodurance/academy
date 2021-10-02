package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.List;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.lang.String.format;
import static java.util.Collections.reverse;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class BagSorter {

    private final BagSearcher bagSearcher;

    public BagSorter(BagSearcher bagSearcher) {
        this.bagSearcher = bagSearcher;
    }

    public List<Bag> reorganiseBagsByCategory(List<Bag> bags) {
        List<Bag> sortedBags = getEmptyCategorisedBags(bags);
        List<Item> items = getChronologicalOrderedItems(bags);
        for (Item item : items) {
            Bag bag = getBagForItemReassignment(sortedBags, item);
            bag.addItem(item);
        }

        return sortedBags;
    }

    private List<Bag> getEmptyCategorisedBags(List<Bag> bags) {
        return bags
                .stream()
                .map(bag -> new Bag(bag.getIdentifier(), bag.getItemCategory(), bag.getCapacity()))
                .collect(toList());
    }

    private List<Item> getChronologicalOrderedItems(List<Bag> bags) {
        List<Item> items = bags.stream().flatMap(bag -> bag.getItems().stream()).collect(toList());
        reverse(items);
        return items;
    }

    private Bag getBagForItemReassignment(List<Bag> sortedBags, Item item) {
        ItemCategory category = item.getCategory();

        return bagSearcher
                .findAvailableBagForItemReassignment(sortedBags, category)
                .orElseThrow(() -> new UnableToSortItemException(format("Unable to sort item %s due to insufficient storage", item.getName())));
    }

    public List<Bag> reorganiseBagsAlphabetically(List<Bag> bags) {
        for (Bag bag : bags) {
            List<Item> items = bag.getItems();
            List<Item> sortedItems = sortItemNamesAlphabetically(items);
            bag.replaceItems(sortedItems);
        }

        return bags;
    }

    private List<Item> sortItemNamesAlphabetically(List<Item> items) {
        return items
                .stream()
                .sorted(comparing(Item::getName, CASE_INSENSITIVE_ORDER))
                .collect(toList());
    }

}

package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.util.Collections.reverse;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class BagSorter {

    public List<Bag> reorganiseBagsByCategory(List<Bag> bags) {
        HashMap<ItemCategory, Bag> sortedBags = createSortedBags(bags);
        List<Item> items = bags.stream().flatMap(bag -> bag.getItems().stream()).collect(toList());
        reverse(items);

        for (Item item : items) {
            Bag sortedBag = findAppropriateBagToPlace(sortedBags, item);
            sortedBag.addItem(item);
        }

        return new ArrayList<>(sortedBags.values());
    }

    private Bag findAppropriateBagToPlace(HashMap<ItemCategory, Bag> sortedBags, Item item) {
        ItemCategory category = item.getCategory();
        Bag bag = sortedBags.get(category);
        if (sortedBags.containsKey(category) && bag.hasCapacity()) return bag;

        return sortedBags.get(MISCELLANEOUS);
    }

    private HashMap<ItemCategory, Bag> createSortedBags(List<Bag> bags) {
        HashMap<ItemCategory, Bag> sortedBag = new HashMap<>();
        for (Bag bag : bags) {
            ItemCategory category = bag.getItemCategory();
            Bag copiedBag = new Bag(bag.getIdentifier(), category, bag.getCapacity());
            sortedBag.put(category, copiedBag);
        }

        return sortedBag;
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

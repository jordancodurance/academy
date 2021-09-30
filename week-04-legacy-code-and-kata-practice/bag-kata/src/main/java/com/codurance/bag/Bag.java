package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Bag {

    private final BagIdentifier identifier;
    private final List<Item> items = new ArrayList<>();
    private final ItemCategory itemCategory;
    private final int capacity;

    public Bag(BagIdentifier identifier, ItemCategory itemCategory, int capacity) {
        this.identifier = identifier;
        this.itemCategory = itemCategory;
        this.capacity = capacity;
    }

    public BagIdentifier getIdentifier() {
        return identifier;
    }

    public List<String> getContents() {
        return items
                .stream()
                .map(Item::getName)
                .collect(toList());
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean hasCapacity() {
        return items.size() < capacity;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void replaceItems(List<Item> newItems) {
        items.clear();
        items.addAll(newItems);
    }

}

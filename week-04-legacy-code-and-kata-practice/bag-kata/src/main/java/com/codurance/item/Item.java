package com.codurance.item;

public class Item {

    private final String name;
    private final ItemCategory category;

    public Item(String name, ItemCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public ItemCategory getCategory() {
        return category;
    }

}

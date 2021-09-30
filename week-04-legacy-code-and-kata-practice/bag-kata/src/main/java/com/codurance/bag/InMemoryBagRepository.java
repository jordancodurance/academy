package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryBagRepository implements BagRepository {

    private final HashMap<BagIdentifier, Bag> bagStore = new HashMap<>() {
    };

    private BagIdentifier lastAddedBagIdentifier = new BagIdentifier(0);

    @Override
    public BagIdentifier addBag(ItemCategory itemCategory, int capacity) {
        int currentBagIdentifier = lastAddedBagIdentifier.get();
        BagIdentifier newBagIdentifier = new BagIdentifier(currentBagIdentifier + 1);

        Bag bag = new Bag(newBagIdentifier, itemCategory, capacity);
        bagStore.put(newBagIdentifier, bag);

        lastAddedBagIdentifier = newBagIdentifier;

        return newBagIdentifier;
    }

    @Override
    public List<Bag> getBags() {
        return new ArrayList<>(bagStore.values());
    }

    @Override
    public void addItem(BagIdentifier identifier, Item item) {
        Bag bag = bagStore.get(identifier);
        bag.addItem(item);
    }

    @Override
    public void replaceBags(List<Bag> bags) {
        bagStore.clear();

        for (Bag bag : bags)
            bagStore.put(bag.getIdentifier(), bag);
    }

}

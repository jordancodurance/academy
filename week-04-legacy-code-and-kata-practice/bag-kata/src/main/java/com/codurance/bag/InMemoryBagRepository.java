package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codurance.item.ItemCategory.MISCELLANEOUS;

public class InMemoryBagRepository implements BagRepository {

    private static final BagIdentifier BACKPACK_IDENTIFIER = new BagIdentifier(100);
    private static final int BACKPACK_CAPACITY = 8;
    private static final int EXTRA_BAG_CAPACITY = 4;

    private final HashMap<BagIdentifier, Bag> bagStore = new HashMap<>() {{
        put(BACKPACK_IDENTIFIER, new Bag(BACKPACK_IDENTIFIER, MISCELLANEOUS, BACKPACK_CAPACITY));
    }};

    private BagIdentifier lastAddedBagIdentifier = BACKPACK_IDENTIFIER;

    @Override
    public List<Bag> getBags() {
        return new ArrayList<>(bagStore.values());
    }

    @Override
    public void addItems(BagIdentifier identifier, List<Item> items) {
        Bag bag = bagStore.get(identifier);
        bag.addItems(items);
    }

    @Override
    public void replaceBags(List<Bag> bags) {
        bagStore.clear();

        for (Bag bag : bags)
            bagStore.put(bag.getIdentifier(), bag);
    }

    @Override
    public BagIdentifier addExtraBag(ItemCategory itemCategory) {
        int currentBagIdentifier = lastAddedBagIdentifier.get();
        BagIdentifier newBagIdentifier = new BagIdentifier(currentBagIdentifier + 1);

        Bag bag = new Bag(newBagIdentifier, itemCategory, EXTRA_BAG_CAPACITY);
        bagStore.put(newBagIdentifier, bag);

        lastAddedBagIdentifier = newBagIdentifier;

        return newBagIdentifier;
    }

}

package com.codurance.bag;

import java.util.List;

import static com.codurance.item.ItemCategory.MISCELLANEOUS;

public class BagManager {

    private static final BagIdentifier BACKPACK_IDENTIFIER = new BagIdentifier(100);
    private static final int BACKPACK_CAPACITY = 8;
    private static final int EXTRA_BAG_CAPACITY = 4;

    private final BagRepository bagRepository;

    public BagManager(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public BagIdentifier getNextAvailableBag(List<Bag> bags) {
        int bagCount = bags.size();
        Bag currentBag = bags.get(bagCount - 1);
        BagIdentifier currentBagIdentifier = currentBag.getIdentifier();

        if (isBackpackUnderCapacity(currentBag)) return currentBagIdentifier;

        if (currentBag.getCapacity() < EXTRA_BAG_CAPACITY) return currentBagIdentifier;

        return bagRepository.addExtraBag(MISCELLANEOUS);
    }

    private boolean isBackpackUnderCapacity(Bag bag) {
        return bag.getIdentifier().equals(BACKPACK_IDENTIFIER) && bag.getCapacity() < BACKPACK_CAPACITY;
    }

}

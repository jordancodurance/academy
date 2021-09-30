package com.codurance.bag;

import java.util.List;

import static com.codurance.item.ItemCategory.MISCELLANEOUS;

public class BagManager {

    private final BagRepository bagRepository;

    public BagManager(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public BagIdentifier getNextAvailableBag(List<Bag> bags) {
        int bagCount = bags.size();
        Bag currentBag = bags.get(bagCount - 1);
        BagIdentifier currentBagIdentifier = currentBag.getIdentifier();

        if (currentBag.hasCapacity()) return currentBagIdentifier;

        return bagRepository.addExtraBag(MISCELLANEOUS);
    }

}

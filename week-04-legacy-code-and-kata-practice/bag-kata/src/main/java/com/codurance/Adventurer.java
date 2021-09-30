package com.codurance;

import com.codurance.bag.Bag;
import com.codurance.bag.BagIdentifier;
import com.codurance.bag.BagManager;
import com.codurance.bag.BagRepository;
import com.codurance.bag.BagSorter;
import com.codurance.item.Item;

import java.util.List;

public class Adventurer {

    private final BagRepository bagRepository;
    private final BagManager bagManager;
    private final BagSorter bagSorter;

    public Adventurer(BagRepository bagRepository, BagManager bagManager, BagSorter bagSorter) {
        this.bagRepository = bagRepository;
        this.bagManager = bagManager;
        this.bagSorter = bagSorter;
    }

    public void storeItems(List<Item> items) {
        List<Bag> currentBags = bagRepository.getBags();
        BagIdentifier nextAvailableBagIdentifier = bagManager.getNextAvailableBag(currentBags);

        bagRepository.addItems(nextAvailableBagIdentifier, items);
    }

    public void castOrganise() {
        List<Bag> currentBags = bagRepository.getBags();
        currentBags = bagSorter.reorganiseBagsByCategory(currentBags);
        currentBags = bagSorter.reorganiseBagsAlphabetically(currentBags);

        bagRepository.replaceBags(currentBags);
    }

    public List<Bag> getBags() {
        return bagRepository.getBags();
    }

}

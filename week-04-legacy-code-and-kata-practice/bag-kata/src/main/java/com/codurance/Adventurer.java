package com.codurance;

import com.codurance.bag.Bag;
import com.codurance.bag.BagManager;
import com.codurance.bag.BagSorter;
import com.codurance.item.Item;

import java.util.List;

public class Adventurer {

    private final BagManager bagManager;
    private final BagSorter bagSorter;

    public Adventurer(BagManager bagManager, BagSorter bagSorter) {
        this.bagManager = bagManager;
        this.bagSorter = bagSorter;
    }

    public void startAdventure() {
        bagManager.assembleStartingBags();
    }

    public void storeItems(List<Item> items) {
        bagManager.addItemsToAvailableBag(items);
    }

    public void castOrganise() {
        List<Bag> currentBags = getBags();
        currentBags = bagSorter.reorganiseBagsByCategory(currentBags);
        currentBags = bagSorter.reorganiseBagsAlphabetically(currentBags);

        bagManager.replaceCurrentBags(currentBags);
    }

    public List<Bag> getBags() {
        return bagManager.getCurrentBags();
    }

}

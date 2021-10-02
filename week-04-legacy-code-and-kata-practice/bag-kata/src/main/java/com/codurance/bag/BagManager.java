package com.codurance.bag;

import com.codurance.item.Item;

import java.util.List;

import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static com.codurance.item.ItemCategory.WEAPONS;

public class BagManager {

    private static final int BACKPACK_CAPACITY = 8;
    private static final int EXTRA_BAG_CAPACITY = 4;

    private final BagRepository bagRepository;

    public BagManager(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public void assembleStartingBags() {
        bagRepository.addBag(MISCELLANEOUS, BACKPACK_CAPACITY);
        bagRepository.addBag(METALS, EXTRA_BAG_CAPACITY);
        bagRepository.addBag(MISCELLANEOUS, EXTRA_BAG_CAPACITY);
        bagRepository.addBag(WEAPONS, EXTRA_BAG_CAPACITY);
        bagRepository.addBag(MISCELLANEOUS, EXTRA_BAG_CAPACITY);
    }

    public void addItemsToAvailableBag(List<Item> items) {
        for (Item item : items) {
            List<Bag> currentBags = bagRepository.getBags();
            BagIdentifier nextAvailableBagIdentifier = getNextAvailableBag(currentBags);

            bagRepository.addItem(nextAvailableBagIdentifier, item);
        }
    }

    private BagIdentifier getNextAvailableBag(List<Bag> bags) {
        return bags
                .stream()
                .filter(Bag::hasCapacity)
                .findFirst()
                .map(Bag::getIdentifier)
                .orElseThrow(InventoryFullException::new);
    }

    public List<Bag> getCurrentBags() {
        return bagRepository.getBags();
    }

    public void replaceCurrentBags(List<Bag> bags) {
        bagRepository.replaceBags(bags);
    }

}

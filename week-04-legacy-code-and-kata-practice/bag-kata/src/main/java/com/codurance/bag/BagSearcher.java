package com.codurance.bag;

import com.codurance.item.ItemCategory;

import java.util.List;
import java.util.Optional;

import static com.codurance.item.ItemCategory.MISCELLANEOUS;

public class BagSearcher {

    public Optional<Bag> findAvailableBagForItemReassignment(List<Bag> bags, ItemCategory category) {
        Optional<Bag> categorisedBag = findCategorisedBagWithCapacity(bags, category);
        if (categorisedBag.isPresent()) return categorisedBag;

        return findCategorisedBagWithCapacity(bags, MISCELLANEOUS);
    }

    private Optional<Bag> findCategorisedBagWithCapacity(List<Bag> bags, ItemCategory category) {
        return bags
                .stream()
                .filter(bag -> bag.getItemCategory() == category && bag.hasCapacity())
                .findFirst();
    }

}

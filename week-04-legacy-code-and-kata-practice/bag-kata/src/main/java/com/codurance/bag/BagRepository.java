package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.List;

public interface BagRepository {

    List<Bag> getBags();

    void addItems(BagIdentifier identifier, List<Item> items);

    void replaceBags(List<Bag> bags);

    BagIdentifier addExtraBag(ItemCategory itemCategory);

}

package com.codurance.bag;

import com.codurance.item.Item;
import com.codurance.item.ItemCategory;

import java.util.List;

public interface BagRepository {

    BagIdentifier addBag(ItemCategory itemCategory, int capacity);

    List<Bag> getBags();

    void addItem(BagIdentifier identifier, Item item);

    void replaceBags(List<Bag> bags);

}

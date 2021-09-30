package com.codurance.bag;

import com.codurance.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BagManagerShould {

    @Mock
    private BagRepository bagRepository;

    private BagManager target;

    @BeforeEach
    void setUp() {
        target = new BagManager(bagRepository);
    }

    @Test
    void create_backpack_for_starting_bags() {
        target.assembleStartingBags();

        verify(bagRepository).addBag(MISCELLANEOUS, 8);
    }

    @Test
    void add_item_to_current_bag_when_bag_under_capacity() {
        BagIdentifier bagIdentifier = new BagIdentifier(100);
        Bag bag = createBagWithCapacity(bagIdentifier, 4, 3);
        List<Bag> bags = of(bag);
        Item item = new Item("Leather", CLOTHES);
        List<Item> newItems = of(item);
        given(bagRepository.getBags()).willReturn(bags);

        target.addItemsToAvailableBag(newItems);

        verify(bagRepository).addItem(bagIdentifier, item);
    }

    @Test
    void add_item_to_new_bag_when_current_bag_at_capacity() {
        Bag extraBag = createBagWithCapacity(new BagIdentifier(101), 4, 4);
        List<Bag> bags = of(extraBag);
        Item item = new Item("Leather", CLOTHES);
        List<Item> newItems = of(item);
        BagIdentifier newBagIdentifier = new BagIdentifier(102);
        given(bagRepository.getBags()).willReturn(bags);
        given(bagRepository.addBag(MISCELLANEOUS, 4)).willReturn(newBagIdentifier);

        target.addItemsToAvailableBag(newItems);

        verify(bagRepository).addItem(newBagIdentifier, item);
    }

    private Bag createBagWithCapacity(BagIdentifier bagIdentifier, int maxCapacity, int currentCapacity) {
        Bag bag = new Bag(bagIdentifier, MISCELLANEOUS, maxCapacity);
        for (int i = 0; i < currentCapacity; i++) {
            bag.addItem(
                    new Item("Item name", CLOTHES)
            );
        }
        return bag;
    }

    @Test
    void retrieve_current_bags_persisted() {
        List<Bag> expectedBags = of(
                createBagWithCapacity(new BagIdentifier(100), 8, 3)
        );
        given(bagRepository.getBags()).willReturn(expectedBags);

        List<Bag> bags = target.getCurrentBags();

        assertEquals(expectedBags, bags);
    }

    @Test
    void replace_current_persisted_bags() {
        List<Bag> newBags = of(
                createBagWithCapacity(new BagIdentifier(100), 8, 7)
        );

        target.replaceCurrentBags(newBags);

        verify(bagRepository).replaceBags(newBags);
    }

}
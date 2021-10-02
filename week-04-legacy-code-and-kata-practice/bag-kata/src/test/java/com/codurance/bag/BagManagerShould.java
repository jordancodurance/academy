package com.codurance.bag;

import com.codurance.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static com.codurance.item.ItemCategory.METALS;
import static com.codurance.item.ItemCategory.MISCELLANEOUS;
import static com.codurance.item.ItemCategory.WEAPONS;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
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
    void assemble_starting_bags() {
        target.assembleStartingBags();

        InOrder inOrder = inOrder(bagRepository);
        inOrder.verify(bagRepository).addBag(MISCELLANEOUS, 8);
        inOrder.verify(bagRepository).addBag(METALS, 4);
        inOrder.verify(bagRepository).addBag(MISCELLANEOUS, 4);
        inOrder.verify(bagRepository).addBag(WEAPONS, 4);
        inOrder.verify(bagRepository).addBag(MISCELLANEOUS, 4);
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
    void add_item_to_next_bag_when_current_bag_at_capacity() {
        Bag firstBag = createBagWithCapacity(new BagIdentifier(101), 1, 1);
        BagIdentifier secondBagIdentifier = new BagIdentifier(102);
        Bag secondBag = createBagWithCapacity(secondBagIdentifier, 1, 0);
        List<Bag> bags = of(firstBag, secondBag);
        Item item = new Item("Leather", CLOTHES);
        List<Item> newItems = of(item);
        given(bagRepository.getBags()).willReturn(bags);

        target.addItemsToAvailableBag(newItems);

        verify(bagRepository).addItem(secondBagIdentifier, item);
    }

    @Test
    void block_storing_an_item_when_all_bags_are_full() {
        Bag firstBag = createBagWithCapacity(new BagIdentifier(101), 1, 1);
        Bag secondBag = createBagWithCapacity(new BagIdentifier(102), 1, 1);
        List<Bag> bags = of(firstBag, secondBag);
        List<Item> newItems = of(new Item("Leather", CLOTHES));
        given(bagRepository.getBags()).willReturn(bags);

        assertThrows(InventoryFullException.class, () -> target.addItemsToAvailableBag(newItems));
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

    private Bag createBagWithCapacity(BagIdentifier bagIdentifier, int maxCapacity, int currentCapacity) {
        Bag bag = new Bag(bagIdentifier, MISCELLANEOUS, maxCapacity);
        for (int i = 0; i < currentCapacity; i++) {
            bag.addItem(
                    new Item("Item name", CLOTHES)
            );
        }
        return bag;
    }

}
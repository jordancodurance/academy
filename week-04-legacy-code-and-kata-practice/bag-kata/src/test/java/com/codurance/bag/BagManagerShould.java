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
    void return_backpack_identifier_for_next_available_bag_when_under_capacity() {
        BagIdentifier backpackIdentifier = new BagIdentifier(100);
        Bag backpack = createBagWithCapacity(backpackIdentifier, 7);
        List<Bag> bags = of(backpack);

        BagIdentifier nextAvailableBagIdentifier = target.getNextAvailableBag(bags);

        assertEquals(backpack.getIdentifier(), nextAvailableBagIdentifier);
    }

    @Test
    void return_new_created_bag_identifier_when_backpack_at_capacity() {
        BagIdentifier backpackIdentifier = new BagIdentifier(100);
        Bag backpack = createBagWithCapacity(backpackIdentifier, 8);
        List<Bag> bags = of(backpack);
        BagIdentifier newBagIdentifier = new BagIdentifier(101);
        given(bagRepository.addExtraBag(MISCELLANEOUS)).willReturn(newBagIdentifier);

        BagIdentifier nextAvailableBagIdentifier = target.getNextAvailableBag(bags);

        assertEquals(newBagIdentifier, nextAvailableBagIdentifier);
    }

    @Test
    void return_new_created_bag_when_extra_bag_at_capacity() {
        BagIdentifier nonBackpackIdentifier = new BagIdentifier(101);
        Bag extraBag = createBagWithCapacity(nonBackpackIdentifier, 4);
        List<Bag> bags = of(extraBag);
        BagIdentifier newBagIdentifier = new BagIdentifier(102);
        given(bagRepository.addExtraBag(MISCELLANEOUS)).willReturn(newBagIdentifier);

        BagIdentifier nextAvailableBagIdentifier = target.getNextAvailableBag(bags);

        assertEquals(newBagIdentifier, nextAvailableBagIdentifier);
    }

    private Bag createBagWithCapacity(BagIdentifier bagIdentifier, int capacity) {
        Bag bag = new Bag(bagIdentifier, MISCELLANEOUS, capacity);
        for (int i = 0; i < capacity; i++) {
            bag.addItem(
                    new Item("Item name", CLOTHES)
            );
        }
        return bag;
    }

}
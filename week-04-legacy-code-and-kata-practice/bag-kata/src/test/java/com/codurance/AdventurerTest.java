package com.codurance;

import com.codurance.bag.Bag;
import com.codurance.bag.BagManager;
import com.codurance.bag.BagSorter;
import com.codurance.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.item.ItemCategory.CLOTHES;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdventurerTest {

    @Mock
    private BagManager bagManager;

    @Mock
    private BagSorter bagSorter;

    private Adventurer target;

    @BeforeEach
    void setUp() {
        target = new Adventurer(bagManager, bagSorter);
    }

    @Test
    void assemble_starting_bags_when_starting_an_adventure() {
        target.startAdventure();

        verify(bagManager).assembleStartingBags();
    }

    @Test
    void add_new_items_to_next_available_bag() {
        List<Item> newItems = of(
                new Item("Leather", CLOTHES)
        );

        target.storeItems(newItems);

        verify(bagManager).addItemsToAvailableBag(newItems);
    }

    @Test
    void reorganise_current_bags() {
        List<Bag> persistedBags = new ArrayList<>();
        List<Bag> categoricallySortedBags = new ArrayList<>();
        List<Bag> alphabeticallySortedBags = new ArrayList<>();
        given(bagManager.getCurrentBags()).willReturn(persistedBags);
        given(bagSorter.reorganiseBagsByCategory(persistedBags)).willReturn(categoricallySortedBags);
        given(bagSorter.reorganiseBagsAlphabetically(categoricallySortedBags)).willReturn(alphabeticallySortedBags);

        target.castOrganise();

        verify(bagManager).replaceCurrentBags(alphabeticallySortedBags);
    }

    @Test
    void retrieve_bags_persisted() {
        List<Bag> expectedBags = new ArrayList<>();
        given(bagManager.getCurrentBags()).willReturn(expectedBags);

        List<Bag> bags = target.getBags();

        assertEquals(expectedBags, bags);
    }

}
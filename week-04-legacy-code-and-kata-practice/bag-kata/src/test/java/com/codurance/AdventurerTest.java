package com.codurance;

import com.codurance.bag.Bag;
import com.codurance.bag.BagIdentifier;
import com.codurance.bag.BagManager;
import com.codurance.bag.BagRepository;
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
    private BagRepository bagRepository;

    @Mock
    private BagManager bagManager;

    @Mock
    private BagSorter bagSorter;

    private Adventurer target;

    @BeforeEach
    void setUp() {
        target = new Adventurer(bagRepository, bagManager, bagSorter);
    }

    @Test
    void add_new_items_to_next_available_bag() {
        List<Bag> persistedBags = new ArrayList<>();
        BagIdentifier expectedBagIdentifier = new BagIdentifier(1001);
        given(bagRepository.getBags()).willReturn(persistedBags);
        given(bagManager.getNextAvailableBag(persistedBags)).willReturn(expectedBagIdentifier);
        List<Item> newItems = of(new Item("Leather", CLOTHES));

        target.storeItems(newItems);

        verify(bagRepository).addItems(expectedBagIdentifier, newItems);
    }

    @Test
    void reorganise_current_bags() {
        List<Bag> persistedBags = new ArrayList<>();
        List<Bag> categoricallySortedBags = new ArrayList<>();
        List<Bag> alphabeticallySortedBags = new ArrayList<>();
        given(bagRepository.getBags()).willReturn(persistedBags);
        given(bagSorter.reorganiseBagsByCategory(persistedBags)).willReturn(categoricallySortedBags);
        given(bagSorter.reorganiseBagsAlphabetically(categoricallySortedBags)).willReturn(alphabeticallySortedBags);

        target.castOrganise();

        verify(bagRepository).replaceBags(alphabeticallySortedBags);
    }

    @Test
    void retrieve_bags_persisted() {
        List<Bag> expectedBags = new ArrayList<>();
        given(bagRepository.getBags()).willReturn(expectedBags);

        List<Bag> bags = target.getBags();

        assertEquals(expectedBags, bags);
    }

}